package com.thread;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tryingpfq
 * @date 2018/11/2 10:34
 */
public abstract class DispatcherExecutor {
    /**
     * 提交任务
     */
    public abstract Future submit(BaseDispatcherTask task);

    /**
     * 定时任务
     */
    public abstract Future scheduleAtFixedRate(BaseDispatcherTask task,long initialDelay, long period, TimeUnit unit);

    /**
     * 延迟任务
     */
    public abstract Future scheduleWithFixedDelay(BaseDispatcherTask task,long initialDelay, long delay, TimeUnit unit);

    /**
     * 根据hash值 在pool中获取一个线程
     */
    public abstract ScheduledExecutorService getScheduledExecutorService(int dHashCode);
}
