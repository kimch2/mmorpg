package com.tryingpfq.net.handler;

import com.annotation.WsController;
import com.annotation.WsRequest;
import com.google.common.collect.Maps;
import com.thread.BaseDispatcherTask;
import com.thread.DispatcherThreadPoolExecutor;
import com.tryingpfq.domain.utils.ChannelUtils;
import com.tryingpfq.net.dispatcher.InvokerDefinition;
import com.tryingpfq.net.dispatcher.InvokerManager;
import com.tryingpfq.packet.AbstractPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tryingpfq
 * @date 2018/11/2 11:47
 */
@Component
@ChannelHandler.Sharable
public class DispatcherHandler extends SimpleChannelInboundHandler implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherHandler.class);

    private static Map<Class<? extends AbstractPacket>,Object> packet2Bean = Maps.newHashMap();
    private static Map<Class<? extends AbstractPacket>,Method> packet2Method = Maps.newHashMap();
    @Autowired
    private DispatcherThreadPoolExecutor dispatcherThreadPoolExecutor;

    @Autowired
    private InvokerManager invokerManager;

    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        final InvokerDefinition invokerDefinition = invokerManager.getInvokerDefinition(msg.getClass());

        dispatcherThreadPoolExecutor.submit(new BaseDispatcherTask() {
            @Override
            public int getDispatcherCode() {
                return ChannelUtils.getChannelSession(ctx.channel()).getId();
            }

            @Override
            public void run() {
                try{
                    Object invoke = invokerDefinition.invoke(ChannelUtils.getChannelSession(ctx.channel()),msg);
                    if(invoke!=null && invoke instanceof AbstractPacket){
                        // TODO 写入并刷星缓存
                        ctx.channel().writeAndFlush(invoke);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    logger.debug("request is error");
                }

            }
        });
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Class<?> clazz = o.getClass();
        //WSController
        Annotation cAnno = clazz.getAnnotation(WsController.class);

        if(cAnno != null){
            for(Method md : clazz.getMethods()){
                Annotation mAnno = md.getAnnotation(WsRequest.class);
                if(mAnno != null){
                    Class<?>[] params = md.getParameterTypes();
                    //第二个参数为对应的请求包
                    Class cl = params[1];
                    packet2Bean.put(cl,o);
                    packet2Method.put(cl,md);
                }
            }
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return null;
    }
}