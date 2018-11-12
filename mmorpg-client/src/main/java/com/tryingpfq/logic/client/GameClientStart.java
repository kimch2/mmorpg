package com.tryingpfq.logic.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author tryingpfq
 * @date 2018/11/8 9:41
 */
@Component
public class GameClientStart {
    private static final Logger logger = LoggerFactory.getLogger(GameClientStart.class);

    private static NetClient netClient = new NetClient();
    public void start(){
        netClient.start();
    }
}
