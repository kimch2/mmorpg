package com.tryingpfq.logic.map.obj;

/**
 * @author tryingpfq
 * @date 2018/12/20 15:47
 * 游戏中的生物对象，是具有生命的对象
 */
public abstract class LifeObject extends MapObject{

    /**
     * 上次死亡时间
     */
    protected long lastDeadTime;

    public abstract short getLevel();

    public long getLastDeadTime() {
        return lastDeadTime;
    }

    public void setLastDeadTime(long lastDeadTime) {
        this.lastDeadTime = lastDeadTime;
    }
}
