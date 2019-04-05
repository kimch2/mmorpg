package com.tryingpfq.common.timer;

import com.tryingpfq.common.timer.task.CronTriggerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tryingpfq
 * @date 2019/3/12 17:04
 */
@Component
public class CommonCron {

    @Autowired
    private IMidNighterService midNighterService;

    @CronTriggerTask(CronTaskType.ZERO_TIME)
    public void onZeroTime(){
        //listener
        midNighterService.zero();
    }


}
