package com.tryingpfq.logic.scene.service;

import com.tryingpfq.logic.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SceneService implements ISceneService{
    private static final Logger logger = LoggerFactory.getLogger(SceneService.class);

    public void loadSceneDataInfo() {
        Scene scene = new Scene();
        scene.initializeData();
        logger.info("场景数据加载完毕");
    }

    @Override
    public void initSceneDataInfo(int sceneId) {

    }
}