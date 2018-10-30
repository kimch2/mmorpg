package com.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by trying on 2018/10/26.
 * 线程处理
 */
public abstract class MagicalGod implements BaseGod{
    private final static Logger log = LoggerFactory.getLogger(MagicalGod.class);

    /** 是否在运行 **/
    private AtomicBoolean running = new AtomicBoolean(false);

    /** 最后执行时间 **/
    private long lastExecuteTimeMillis = System.currentTimeMillis();

    @Override
    public void run() {
        int costTime = 0;

        while(running.get()){
            long lastTimeMillis = System.currentTimeMillis();
            try{
                execute(running.get());
            }catch (Exception e){
                log.error("Exception occure when execute in" + getName(),e);
            }catch (Throwable t){
                log.error("Exception occure when execute in" + getName(),t);
            }

            updateLastExecuteTimeMillis();

            costTime = (int)(lastExecuteTimeMillis - lastTimeMillis);

            if(getMinSleepMillis() > 0){
                try{
                    int sleepMillis = 0;
                    if(getProcessPeriod() <= 0){
                        sleepMillis = getMinSleepMillis();
                    }else {
                        sleepMillis = getProcessPeriod() - costTime;
                        if(sleepMillis < getMinSleepMillis()){
                            sleepMillis = getMinSleepMillis();
                        }
                    }
                    if(sleepMillis > 0){
                        //一定大于0
                        Thread.sleep(sleepMillis);
                    }
                }catch (Exception e){
                   e.printStackTrace();
                }
            }
        }

    }

    protected abstract int getMinSleepMillis();

    protected  void updateLastExecuteTimeMillis(){
        lastExecuteTimeMillis = System.currentTimeMillis();
    }

    /** 这个方法在每个周期别执行一次 **/
    public abstract void execute(boolean running);


    public abstract String getName();

    public abstract int getProcessPeriod();
    public AtomicBoolean getRunning() {
        return running;
    }

    public void setRunning(AtomicBoolean running) {
        this.running = running;
    }

    public long getLastExecuteTimeMillis() {
        return lastExecuteTimeMillis;
    }

    public void setLastExecuteTimeMillis(long lastExecuteTimeMillis) {
        this.lastExecuteTimeMillis = lastExecuteTimeMillis;
    }

    @Override
    public void init() {
        running.set(true);
        updateLastExecuteTimeMillis();
    }

    @Override
    public void stop() {
        running.compareAndSet(true,false);
    }
}
