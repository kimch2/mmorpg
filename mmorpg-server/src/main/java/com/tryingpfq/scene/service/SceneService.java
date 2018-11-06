package com.tryingpfq.scene.service;

import com.tryingpfq.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SceneService implements ISceneService {
    private static final Logger logger = LoggerFactory.getLogger(SceneService.class);

    public void loadSceneDataInfo() {
        Scene scene = new Scene();
        scene.initializeData();

    }

    @Override
    public void initSceneDataInfo(int sceneId) {

    }
}