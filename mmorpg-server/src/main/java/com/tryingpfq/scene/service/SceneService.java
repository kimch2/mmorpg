package com.tryingpfq.scene.service;

import com.tryingpfq.scene.Scene;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

<<<<<<< Updated upstream
@Component
public class SceneService implements ISceneService{
    private static final Logger logger = LoggerFactory.getLogger(SceneService.class);

    public void loadSceneDataInfo() {
        Scene scene = new Scene();
        scene.initializeData();

=======
/**
 * Created by trying on 2018/10/30.
 */
@Component
public class SceneService implements ISceneService {

    private final static Logger logger = LoggerFactory.getLogger(SceneService.class);


    public void initSceneDataInfo(int sceneId) {
        Scene scene = new Scene();
        scene.init();
>>>>>>> Stashed changes
    }
}
