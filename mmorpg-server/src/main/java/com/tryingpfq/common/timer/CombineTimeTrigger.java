package com.tryingpfq.common.timer;

import com.google.common.collect.Lists;

import java.sql.Time;
import java.util.Collection;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:32
 */
public class CombineTimeTrigger implements TimeTrigger {
    private List<TimeTrigger> triggerList = Lists.newArrayList();

    public CombineTimeTrigger(Collection<? extends TimeTrigger> timeTriggerCollection){
        if(timeTriggerCollection.size() == 0){

        }
        triggerList.addAll(timeTriggerCollection);
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
