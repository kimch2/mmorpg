package com.tryingpfq.net.dispatcher;

import com.google.common.collect.Lists;
import com.tryingpfq.domain.Session;
import com.tryingpfq.packet.AbstractPacket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by trying on 2018/11/6.
 * 对请求反射,找到对应方法的参数
 * 然后经过Method.invok方法调用
 * 并将要写入和冲刷的数据返回
 */
public class InvokerDefinition {
    private Object bean;
    private Method method;

    public InvokerDefinition(Object bean,Method method){
        this.bean = bean;
        this.method = method;
    }

    public Object invoke(Session session,Object packet) throws Exception {
        Class<?>[] paramsTyper = method.getParameterTypes();
        List<Object> params = Lists.newArrayList();
        for(Class type : paramsTyper){
            if(type.newInstance() instanceof Session){
                //方法中的参数分别为Session，和 packet
                params.add(session);
            }else if(type.newInstance() instanceof AbstractPacket){
                params.add(packet);
            }else {
                throw new IllegalArgumentException("参数有误，不是session和Abstractpacket");
            }
        }
        try {
            return method.invoke(this.bean,params.toArray());
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
