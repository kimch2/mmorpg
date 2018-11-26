package com.utils;

import org.springframework.core.ResolvableType;

/**
 * @author tryingpfq
 * @date 2018/11/26 16:07
 */
public class ReflectUtils {

    /**
     * ResolvableType 通过反射操作类，以及泛型操作
     * getSuperType()：获取直接父类型
     * getInterfaces()：获取接口类型
     * getGeneric(int...)：获取类型携带的泛型类型
     * resolve()：Type对象到Class对象的转换
     *
     *  forField(Field)：获取指定字段的类型
     *  forMethodParameter(Method, int)：获取指定方法的指定形参的类型
     *  forMethodReturnType(Method)：获取指定方法的返回值的类型
     */

    public static Class<?> getSuperGenericType(Class clazz) {
        ResolvableType resolvableType = ResolvableType.forClass(clazz); //获取泛型信息
        /**
         *  exapmle  xx extends Parent<String,D>
         *  this return will be java.lang.String
         */
        Class<?> resolve = resolvableType.getSuperType().getGeneric(0).resolve();   //父类第0个位置上的泛型实参类型
        return resolve;
    }

    public static Class<?> getInterfactGenericType(Class clazz){
        ResolvableType resolvableType = ResolvableType.forClass(clazz);
        ResolvableType[] interfaces = resolvableType.getInterfaces();   //获取所有实现的接口
        if(interfaces == null || interfaces.length == 0){
            return null;
        }
        Class<?> resolve= interfaces[0].getGeneric(0).resolve();    //获取第一接口的第一个泛型参数类型
        return resolve;
    }
    public static Class<?> getGenericType(Class clazz){
        Class<?> genericType = getSuperGenericType(clazz);
        if(genericType == null){
            genericType = getInterfactGenericType(clazz);
        }
        return genericType;
    }
}
