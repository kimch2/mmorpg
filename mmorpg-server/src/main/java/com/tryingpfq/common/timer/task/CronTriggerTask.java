package com.tryingpfq.common.timer.task;

import com.tryingpfq.common.timer.CronTaskType;

import java.lang.annotation.*;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CronTriggerTask {
    CronTaskType value();
}
