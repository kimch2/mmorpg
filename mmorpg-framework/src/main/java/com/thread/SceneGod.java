package com.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by trying on 2018/10/30.
 * 处理场景业务
 * 该类维护两组线程队列和两组线程池
 */
public class SceneGod {

    private final static Logger log = LoggerFactory.getLogger(SceneGod.class);

    private final static SceneGod instance = new SceneGod();

    /** BOSS 线程池 和线程池中的服务 **/
    private final static ExecutorService bossThreadPool = Executors.newCachedThreadPool();

    private ConcurrentLinkedQueue<BaseGod> bossServices = new ConcurrentLinkedQueue<BaseGod>();

    /** WORKER 线程池 和服务 **/
    private final static ExecutorService workerThreadPool = Executors.newCachedThreadPool();

    private ConcurrentLinkedQueue<BaseGod> workerServices = new ConcurrentLinkedQueue<BaseGod>();

    public AtomicBoolean isStop = new AtomicBoolean(false);

    private SceneGod(){

    }
    /** 单例 **/
    public static SceneGod getInstance(){
        return instance;
    }

    public boolean executeWorker(BaseGod baseGod){
        if(this.isStop.get())
            return false;
        baseGod.init();
        workerThreadPool.execute(baseGod);
        return true;
    }

    public void stop(){
        if(this.isStop.compareAndSet(false,true)){
            stopThreads(bossThreadPool,bossServices);
            stopThreads(workerThreadPool,workerServices);
        }
    }
    public void stopThreads(ExecutorService pool,ConcurrentLinkedQueue<BaseGod> services){

    }

}
