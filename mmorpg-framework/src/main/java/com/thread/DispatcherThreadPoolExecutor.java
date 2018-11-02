package com.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tryingpfq
 * @date 2018/11/2 10:47
 */
public class DispatcherThreadPoolExecutor extends DispatcherExecutor{
    private ScheduledExecutorService[] threadPool;

/*    public static DispatcherExecutor getGlobalDispatcherExecutor(){
        return this;
    }*/
    public DispatcherThreadPoolExecutor(int threadSize){
        threadPool = new ScheduledExecutorService[threadSize];
        for(int i= 0; i< threadSize ;i++){
            threadPool[i] = Executors.newSingleThreadScheduledExecutor();
        }
    }
    @Override
    public Future submit(BaseDispatcherTask task) {
        ScheduledExecutorService scheduledExecutorService = getScheduledExecutorService(task.getDispatcherCode());
        return scheduledExecutorService.submit(task);
    }

    @Override
    public Future scheduleAtFixedRate(BaseDispatcherTask task, long initialDelay, long period, TimeUnit unit) {
        ScheduledExecutorService scheduledExecutorService = getScheduledExecutorService(task.getDispatcherCode());
        return scheduledExecutorService.scheduleAtFixedRate(task,initialDelay,period,unit);
    }

    @Override
    public Future scheduleWithFixedDelay(BaseDispatcherTask task, long initialDelay, long delay, TimeUnit unit) {
        ScheduledExecutorService scheduledExecutorService = getScheduledExecutorService(task.getDispatcherCode());
        return scheduledExecutorService.scheduleWithFixedDelay(task,initialDelay,delay,unit);
    }

    @Override
    public ScheduledExecutorService getScheduledExecutorService(int dHashCode) {
        return threadPool[dHashCode % threadPool.length];
    }
}
