package com.thread;

import com.message.IBusinessMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tryingpfq
 * @date 2018/11/23 12:01
 */
public abstract class AbstractBusinessService implements IBussinessService{
    private static final Logger logger = LoggerFactory.getLogger(AbstractBusinessService.class);

    private BlockingQueue<IBusinessMessage> messages = new LinkedBlockingQueue<IBusinessMessage>();

    private AtomicBoolean running = new AtomicBoolean(false);

    private Thread runThread;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void start() {
        if(running.compareAndSet(false,true)){
            runThread = new Thread(this,this.getName());
            runThread.start();
        }
    }

    @Override
    public void stop() throws InterruptedException {
        if(running.compareAndSet(true,false)){
            runThread.join();
        }
    }

    @Override
    public Thread getThread() {
        return runThread;
    }

    @Override
    public boolean pushMessage(IBusinessMessage message) {
        if(running.get()){
            if(runThread == Thread.currentThread()){
                message.execute();
                return true;
            }
            return messages.offer(message);
        }
        return false;
    }

    @Override
    public void run() {
        while(running.get() && !messages.isEmpty()){
            IBusinessMessage message = messages.poll();
            if(message == null)
                continue;

            message.execute();
        }

    }

    @PostConstruct
    public void startThread(){
        start();
    }
}
