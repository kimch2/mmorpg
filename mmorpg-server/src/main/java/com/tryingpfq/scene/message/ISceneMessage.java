package com.tryingpfq.scene.message;

import com.tryingpfq.base.IMessage;
import com.tryingpfq.scene.Scene;

/**
 * 场景信息
 */
public interface ISceneMessage extends IMessage {
    /**
     * 执行消息
     */
    public void execute(Scene curScene);
}
