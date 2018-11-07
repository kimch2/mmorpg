package com.tryingpfq.common.domain;

public enum GameSessionStatus {
    /**
     * 初始
     */
    INIT,

    /**
     * 已经验证
     */
    LOGIN_AUTH,

    /**
     * 正在进入场景(LOGOUTINT还没初始化玩家数据)
     */
    ENTERING_SCENE,

    /**
     * 已经进入场景（数据已经加载）
     */
    ENTERED_SCENE,

    /**
     * 推出
     */

}
