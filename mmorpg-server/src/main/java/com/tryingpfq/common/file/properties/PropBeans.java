package com.tryingpfq.common.file.properties;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.tryingpfq.common.file.FileLoader;
import com.tryingpfq.common.file.properties.annonation.PropKey;
import com.tryingpfq.common.file.properties.annonation.PropLoader;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;

import static org.springframework.cglib.core.TypeUtils.upperFirst;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:38
 */
public class PropBeans {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropBeans.class);

    private static LoadingCache<Class,Prop<Object>> beansCache = CacheBuilder
            .newBuilder()
            .build(new CacheLoader<Class, Prop<Object>>() {
                @Override
                public Prop<Object> load(Class key) throws Exception {
                    return create(key);
                }
            });

    public static <T> Prop<T> getBean(final Class<T> beanClazz){
        try{
            return (Prop<T>) beansCache.get(beanClazz);
        } catch (ExecutionException e) {
            LOGGER.error("加载配置文件失败");
            e.printStackTrace();
        }
        return null;
    }



    private static <T> Prop<T> create(final Class<T> beanClazz){
        PropLoader anno  = beanClazz.getAnnotation(PropLoader.class);
        if(anno == null){
            LOGGER.error("beanClazz {} 尝试加载pro配置文件,可为配置@PropLoader注解",beanClazz);
            return null;
        }
        try {
            String path = anno.value();
            final ProConfig config = new ProConfig();
            final Prop<T> prop = newPropInstance(beanClazz,config);
            config.addListener(new ProConfigListener() {
                @Override
                public void reload(ProConfig proConfig) {
                    T t = initBean(proConfig,beanClazz);
                    T original = prop.original();
                    prop.update(t);
                    if(t instanceof ProConfig){
                        ((PropUpdateListener) t).afterUpdate(original,proConfig);
                    }
                }
            });
            FileLoader.load(path,config);
        }catch (Exception e){
            LOGGER.warn("{} 配置文件创建失败",beanClazz);
        }
       return null;
    }

    private static <T> T initBean(ProConfig proConfig,Class<T> beanClazz){
        try{
            T t = beanClazz.newInstance();
            for(Field field : beanClazz.getDeclaredFields()){
                initFieldValue(t,field,proConfig);
            }
            return t;
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info("加载失败");
            return null;
        }
    }

    private static <T> void initFieldValue(T t, Field field, ProConfig proConfig) throws IllegalAccessException {
        PropKey fieldAnno = field.getAnnotation(PropKey.class);
        if(fieldAnno == null){
            LOGGER.info("{}没有加@PropKey注解",field);
            return;
        }
        String fieldName = fieldAnno.value();
        String defalutVal = fieldAnno.defaultVal().trim();
        String configVal = proConfig.getStrVal(fieldName,"").trim();
        if(StringUtils.isEmpty(configVal)){
            configVal = defalutVal;
        }
        Method setter = getSetterMethod(t,field);
        if(setter != null){
            Class<?>[] parameterTypes = setter.getParameterTypes();
            Class<?> setParamClazz = parameterTypes[0];
            Object param = transferValue(setParamClazz,configVal);
            ReflectionUtils.invokeMethod(setter,t,param);
        }else{
            // if not have setter while derictet set
            boolean accessible = field.isAccessible();
            if(!accessible){
                field.setAccessible(true);
            }
            Object param = transferValue(field.getType(),configVal);
            // may error
            if(param != null){
                field.set(t,param);
                if(!accessible){
                    field.setAccessible(true);
                }
            }
        }
    }

    private static Object transferValue(Class<?> type, String str) {
        if(type.equals(String.class)){
            return str == null ? "" : str.trim();
        }else{
            boolean empty = null == str || str.trim().length() == 0;
            if(type.equals(int.class) || type.equals(Integer.class)){
                if(empty){
                    return 0;
                }else{
                    return Integer.valueOf(str);
                }
            }else if(type.equals(long.class) || type.equals(Long.class)){
                if(empty){
                    return 0;
                }else{
                    return Long.valueOf(str);
                }
            }else if(type.equals(short.class) || type.equals(Short.class)){
                if(empty){
                    return 0;
                }else{
                    return Short.parseShort(str);
                }
            }else if(type.equals(byte.class) || type.equals(Byte.class)){
                if(empty){
                    return 0;
                }else{
                    return Byte.parseByte(str);
                }
            }else if(type.equals(boolean.class) || type.equals(Boolean.class)){
                if(empty){
                    return Boolean.FALSE;
                }else{
                    return Boolean.parseBoolean(str) || "1".equals(str.trim());
                }
            }else{
                LOGGER.info("不支持的类型{} value : {}",type,str);
            }
        }
        return null;
    }

    private static <T> Method getSetterMethod(T t, Field field) {
        String setterName = "set" + upperFirst(field.getName());
        Method method = ReflectionUtils.findMethod(t.getClass(),setterName,field.getType());
        if(method == null){
            if(field.getType() != String.class){
                method = ReflectionUtils.findMethod(t.getClass(),setterName,String.class);
            }
        }
        return method;
    }

    private static <T> Prop<T> newPropInstance(Class<T> beanClazz, ProConfig config) throws Exception {
        Class<? extends Prop<T>> propClazz = PropWriter.createProp(beanClazz);
        Constructor<? extends Prop<T>> constructor = propClazz.getConstructor(ProConfig.class);
        return constructor.newInstance(config);
    }
}
