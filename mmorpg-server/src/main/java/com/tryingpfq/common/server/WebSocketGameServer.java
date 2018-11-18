package com.tryingpfq.common.server;

import com.tryingpfq.common.net.handler.DispatcherHandler;
import com.tryingpfq.logic.scene.service.SceneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author tryingpfq
 * @date 2018/11/7 11:33
 */
@Component
public class WebSocketGameServer implements IGameServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketGameServer.class);

    private static NetServer netServer = new NetServer();

    @Autowired
    private SceneService sceneService;

    @Autowired
    private DispatcherHandler dispatcherHandler; //目前发现问题 new的话 里面注入的bean为空

    @Override
    public void start() {
        sceneService.loadSceneDataInfo();
        netServer.start(dispatcherHandler);
    }

    @Override
    public void stop() {
        StopWatch sw = new StopWatch();
        sw.start();
        logger.info("StopWatch onServerClose{}", sw.getTotalTimeMillis());
        netServer.close();
    }
}
