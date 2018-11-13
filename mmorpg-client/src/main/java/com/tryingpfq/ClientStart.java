package com.tryingpfq;

import com.google.common.base.Preconditions;
import com.tryingpfq.logic.client.GameClientStart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tryingpfq
 * @date 2018/11/8 9:36
 */
public class ClientStart {
    private static final Logger logger = LoggerFactory.getLogger(ClientStart.class);

    private static ApplicationContext context;

    static{
        context = new ClassPathXmlApplicationContext("clientApplicationContext.xml");
    }

    public static void main(String[] args) {
        Preconditions.checkNotNull(context);
        GameClientStart gameClientStart = context.getBean(GameClientStart.class);
        gameClientStart.start();
    }
}
