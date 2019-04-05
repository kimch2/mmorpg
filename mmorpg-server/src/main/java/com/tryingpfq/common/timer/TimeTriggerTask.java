package com.tryingpfq.common.timer;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:17
 */
public interface TimeTriggerTask {
    boolean canTrigger();

    void trigger(long time);

    long getTirggerTime();
}
