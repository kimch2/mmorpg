package com.tryingpfq.common.timer.task;

import com.tryingpfq.common.timer.*;

import java.text.ParseException;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:18
 */
public abstract class AbstractCronTriggerTask implements TimeTriggerTask {
    private CronTaskType cronTaskType;

    private volatile TimeTrigger timeTrigger;

    public AbstractCronTriggerTask(CronTaskType cronTaskType){
        super();
        this.cronTaskType = cronTaskType;

    }

    private void initTimeTrigger(){
        try{
            this.timeTrigger = TimeTriggerFactory.parseCron(CronTaskConfig.getCron(cronTaskType));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected abstract void handle(long time);

    @Override
    public void trigger(long time) {
        if(timeTrigger.trigger()){
            handle(time);
        }
    }

    @Override
    public boolean canTrigger() {
        return false;
    }


    @Override
    public long getTirggerTime() {
        return 0;
    }
}
