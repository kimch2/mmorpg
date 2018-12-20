package com.tryingpfq.logic.map.obj;

/**
 * @author tryingpfq
 * @date 2018/12/20 15:55
 * 场景中的战斗对象
 */
public abstract class GFighter extends LifeObject{

    /**
     * 最大血量
     */
    private int maxHp;

    /**
     * 当前血量
     */
    private int curHp;

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurHp() {
        return curHp;
    }

    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }
}
