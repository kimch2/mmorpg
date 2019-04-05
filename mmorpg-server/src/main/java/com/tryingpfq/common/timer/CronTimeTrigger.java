package com.tryingpfq.common.timer;

import org.quartz.CronTrigger;

import java.text.ParseException;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:39
 */
public class CronTimeTrigger implements TimeTrigger {
    private long lastFireTime;

    private long fireTime;

    private String timeExp;

    private CronTrigger trigger;

    public CronTimeTrigger(String timeExp) throws ParseException{
        this.timeExp = timeExp;
        long startTime = System.currentTimeMillis();
        this.trigger = new CronTrigger();
        trigger.setCronExpression(timeExp);
        setStartTime(startTime);
    }
    @Override
    public void setStartTime(long startTime) {

    }

    @Override
    public boolean canTrigger() {
        return false;
    }

    @Override
    public boolean trigger() {
        return false;
    }

    @Override
    public long getTriggerTime() {
        return 0;
    }

    @Override
    public boolean trigger(long time) {
        return false;
    }

    @Override
    public long getLastFireTime() {
        return 0;
    }

    @Override
    public long getLeftTime() {
        return 0;
    }
}
