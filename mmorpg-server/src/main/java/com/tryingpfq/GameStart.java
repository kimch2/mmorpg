package com.tryingpfq;

import com.google.common.base.Preconditions;
import com.tryingpfq.common.server.WebSocketGameServer;
import com.tryingpfq.logic.account.packet.ReqLoginPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tryingpfq
 * @date 2018/11/7 11:29
 */
public class GameStart {
    private static final Logger logger = LoggerFactory.getLogger(GameStart.class);

    private static ApplicationContext context;
    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    public static void main(String[] args) {
        Preconditions.checkNotNull(context);
        WebSocketGameServer gameServer = context.getBean(WebSocketGameServer.class);
        gameServer.start();
    }
}
