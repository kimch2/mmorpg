package com.tryingpfq.common.timer;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:21
 */
public interface TimeTrigger {
    void setStartTime(long startTime);

    /**
     * is can trigger
     */
    boolean canTrigger();

    boolean trigger();

    long getTriggerTime();

    boolean trigger(long time);

    long getLastFireTime();

    /**
     * left time
     */
    long getLeftTime();
}
