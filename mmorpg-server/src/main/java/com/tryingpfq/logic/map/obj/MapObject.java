package com.tryingpfq.logic.map.obj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tryingpfq
 * @date 2018/12/20 15:33
 * 地图上带坐标的对象
 */
public abstract class MapObject {
    private static final Logger LOGGER = LoggerFactory.getLogger(MapObject.class);
    /**
     * 地图上对象唯一id
     */
    protected long id;

    /**
     * 所在场景 地图Id
     */
    private int sceneId;

    /**
     * x坐标
     */
    private int x;

    /**
     * y坐标
     */
    private int y;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
