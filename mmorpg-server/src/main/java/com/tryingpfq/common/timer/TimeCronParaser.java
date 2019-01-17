package com.tryingpfq.common.timer;

/**
 * @author tryingpfq
 * @date 2019/1/17 15:21
 */
public class TimeCronParaser {

    private String cron;

    public TimeCronParaser(String cron){
        this.cron = cron;
    }

    public boolean hasMoreRecentMatch(long utime){
        return true;
    }
}
