package com.utils;

import com.tryingpfq.domain.GameSession;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

public class ChannelUtils {

    private static final AttributeKey<GameSession> SESION_LEY = AttributeKey.valueOf("session-key");

    /**
     * 添加会话
     * @param channle
     * @param session
     * @return
     */
    public static final boolean addChannelSession(Channel channle,GameSession session){
        Attribute<GameSession> sessionAttribute = channle.attr(SESION_LEY);
        return sessionAttribute.compareAndSet(null,session);
    }

    /**
     * 获取会话
     * @param channel
     * @return
     */
    public static final GameSession getChannelSession(Channel channel){
        Attribute<GameSession> sessionAttr = channel.attr(SESION_LEY);
        return sessionAttr.get();
    }

    /**
     * 移除会话
     * @param channel
     */
    public static final void removeChannelSession(Channel channel){
        Attribute<GameSession> sessionAttr = channel.attr(SESION_LEY);
        if(sessionAttr != null){
            sessionAttr.remove();
        }
    }

    /**
     * 获取Ip地址
     * @param channel
     * @return
     */
    public static final String getIp(Channel channel){
        return ((InetSocketAddress) channel.remoteAddress()).getAddress().getHostAddress();
    }

    public static final String getAccount(Channel channel){
        GameSession session = getChannelSession(channel);
        if(session != null){
            return session.getAccount();
        }
        return "";
    }
}
