package com.tryingpfq.dao;

import com.google.common.collect.Sets;
import com.tryingpfq.dao.entity.IEntity;
import com.tryingpfq.dao.provider.BaseEntityProvider;
import com.tryingpfq.dao.provider.CacheEntityProvider;
import com.utils.ReflectUtils;
import org.hibernate.boot.MetadataSources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author tryingpfq
 * @date 2018/11/29 9:36
 */
public class EntityProviderSessionFactoryBean extends LocalSessionFactoryBean implements InstantiationAwareBeanPostProcessor,PriorityOrdered {
    private static final Logger  logger = LoggerFactory.getLogger(EntityProviderSessionFactoryBean.class);

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void afterPropertiesSet() throws IOException {
        super.afterPropertiesSet();
        Map<String,BaseEntityProvider> beansOfType = beanFactory.getBeansOfType(BaseEntityProvider.class);
        Set<Class> classes = Sets.newHashSet();
        beansOfType.values().forEach(provider -> {
            Class<?> genericType = ReflectUtils.getGenericType(provider.getClass());
            if(genericType != null){
                classes.add(genericType);
            }
        });
        //TODO Entity模型 -> 数据库映射
        MetadataSources metadataSources = getMetadataSources();
        Collection<Class<?>> entities = metadataSources.getAnnotatedClasses();
        entities.forEach(clazz -> {
            //TODO 如果已经有泛型生成类，就不需要再生成代理类
            if(classes.contains(clazz))
                return;
            Object o = null;
            try{
                Class<?> genericType = ReflectUtils.getGenericType(clazz);
                if(genericType == null){
                    logger.warn("{} 没有继承{} 接口或者没有设置泛型，不能生成相应的BaseEntityProvider",clazz.getName(),IEntity.class.getSimpleName());
                    return;
                }
                //生成代理类
                o = ProviderProxyFactory.getInstance().createBaseEntityProviderProxy(getBaseEntityProviderName(),clazz,genericType);
                //注册到spring容器
                this.beanFactory.registerSingleton(o.getClass().getName(),o);
            }catch (Exception e){
                e.printStackTrace();
            }
        });


    }

    public String getBaseEntityProviderName(){
        return CacheEntityProvider.class.getName();
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE-3;
    }
}
