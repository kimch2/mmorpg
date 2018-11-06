package com.tryingpfq.domain.utils;

import com.tryingpfq.domain.GameSession;
import com.tryingpfq.domain.Session;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

import java.net.InetSocketAddress;

public class ChannelUtils {

    private static final AttributeKey<Session> SESSION_LEY = AttributeKey.valueOf("session-key");

    /**
     * 添加会话
     * @param channle
     * @param session
     * @return
     */
    public static final boolean addChannelSession(Channel channle,Session session){
        Attribute<Session> sessionAttribute = channle.attr(SESSION_LEY);
        return sessionAttribute.compareAndSet(null,session);

    }

    /**
     * 获取会话
     * @param channel
     * @return
     */
    public static final Session getChannelSession(Channel channel){
        Attribute<Session> sessionAttr = channel.attr(SESSION_LEY);
        return sessionAttr.get();
    }

    /**
     * 移除会话
     * @param channel
     */
    public static final void removeChannelSession(Channel channel){
        Attribute<Session> sessionAttr = channel.attr(SESSION_LEY);
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
        GameSession session = (GameSession) getChannelSession(channel);
        if(session != null){
            return session.getAccount();
        }
        return "";
    }
}
