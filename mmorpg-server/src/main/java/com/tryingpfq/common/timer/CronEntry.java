package com.tryingpfq.common.timer;

import com.tryingpfq.common.timer.user.IUserCronEventHandler;

/**
 * @author tryingpfq
 * @date 2019/1/17 15:11
 * 定时匹配
 */
public class CronEntry {
    private TimeCronParaser cronParaser;

    private IUserCronEventHandler handler;

    public CronEntry(String cron,IUserCronEventHandler handler) throws Exception{
        this.cronParaser = new TimeCronParaser(cron);
        this.handler = handler;
    }

    /**
     * 时间匹配
     * @param utime
     * @return
     */
    public boolean hasMoreRecentMatch(long utime){
        return this.cronParaser.hasMoreRecentMatch(utime);
    }

    public IUserCronEventHandler getHandler(){
        return handler;
    }
}
