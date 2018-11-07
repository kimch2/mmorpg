package com.tryingpfq.logic.scene.message;

import com.tryingpfq.logic.base.IMessage;
import com.tryingpfq.logic.scene.Scene;

/**
 * 场景信息
 */
public interface ISceneMessage extends IMessage {
    /**
     * 执行消息
     */
    public void execute(Scene curScene);
}
