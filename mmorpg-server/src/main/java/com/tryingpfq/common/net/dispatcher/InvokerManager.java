package com.tryingpfq.common.net.dispatcher;

import com.annotation.WsController;
import com.annotation.WsRequest;
import com.google.common.collect.Maps;
import com.tryingpfq.common.packet.AbstractPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by trying
 * 2018/11/6.
 * 管理每个packet-->Invoker
 */
@Component
public class InvokerManager implements BeanPostProcessor{
    private static final Logger logger = LoggerFactory.getLogger(InvokerManager.class);

    private Map<Class<? extends AbstractPacket>,InvokerDefinition> packet2Invoker = Maps.newHashMap();


    /*@EventListener
    public void started(ContextRefreshedEvent contextRefreshedEvent){
        //初始化完成后将map转为不可变map
        packet2Invoker= ImmutableMap.copyOf(packet2Invoker);
    }*/

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = bean.getClass();
        //WsController
        Annotation cAnno = clazz.getAnnotation(WsController.class);
        if(cAnno == null) return bean;
        if(cAnno != null){
            for(Method md : clazz.getMethods()){
                Annotation mAnno = md.getAnnotation(WsRequest.class);
                if(mAnno != null){
                    Class<?>[] params = md.getParameterTypes();
                    Class cla = params[1];
                    packet2Invoker.put(cla,new InvokerDefinition(bean,md));
                    continue;
                }else{
                    logger.debug("{}中的{}方法没有消息包参数,不是WsRequest请求",
                            clazz.getName(),md.getName());
                }
            }
        }
        //应该可以用ReflectionUtils.doWithMethods()去处理
        return bean;
    }

    public InvokerDefinition getInvokerDefinition(Class packet){
        return this.packet2Invoker.get(packet);
    }
}
