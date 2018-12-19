package com.tryingpfq.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.ClassWriter;
import org.springframework.asm.MethodVisitor;
import org.springframework.asm.Opcodes;

import static jdk.internal.org.objectweb.asm.Opcodes.ACC_PUBLIC;

/**
 * @author tryingpfq
 * @date 2018/11/29 15:19
 * ASM 动态生成class文件
 */
public class ProviderProxyFactory extends ClassLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderProxyFactory.class);

    private ProviderProxyFactory(){}

    //内部类单类模式
    private static class NewInstance{
        private static final ProviderProxyFactory INSTANCE = new ProviderProxyFactory();
    }

    public static final ProviderProxyFactory getInstance(){
        return NewInstance.INSTANCE;
    }

    /**
     * 创建代理类
     */
    public Object createProxyInstance(String superType,Class entity,Class key){
        Class<?> aClazz = createGenericClass(superType,entity,key);
        Object o = null;
        try {
            o = aClazz.newInstance();
        }catch (Exception e){
            LOGGER.error(String.format("%s<%s,%s>实例化失败", superType, entity.getName(), key.getName()), e);
        }
        return o;
    }

    //创建泛型类，其中第二个参数为泛型参数类型,第三个表示id
    public Class<?> createGenericClass(String superProviderName,Class genericType1,Class genericType2){
        String superName = superProviderName.replaceAll("\\.","/");
        //代理配名
        String className = superName + genericType1.getSimpleName() + genericType2.getSimpleName() + "Proxy";

        String genericName = String.format("L%s<L%s;L%s;>;",superName,genericType1.getName().replaceAll("\\.","/"),
                genericType2.getName().replaceAll("\\.","/"));

        System.out.println("genericName:"+genericName);
        ClassWriter classWriter = new ClassWriter(0);

        //定义类头
        //jdk  public 类名称 泛型签名 父类 接口
        classWriter.visit(Opcodes.V1_8,ACC_PUBLIC,
                className,
                genericName,
                superName,null);

        //定义构造方法
        MethodVisitor mv = classWriter.visitMethod(1,"<init>","()V",null,null);
        mv.visitCode();
        mv.visitVarInsn(jdk.internal.org.objectweb.asm.Opcodes.ALOAD, 0);
        mv.visitMethodInsn(jdk.internal.org.objectweb.asm.Opcodes.INVOKESPECIAL, superName, "<init>", "()V", false);
        mv.visitInsn(jdk.internal.org.objectweb.asm.Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
        byte[] bytes = classWriter.toByteArray();
        return defineClass(className.replaceAll("/", "\\."), bytes, 0, bytes.length);
    }
}
