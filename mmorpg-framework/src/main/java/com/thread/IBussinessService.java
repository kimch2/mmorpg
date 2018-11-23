package com.thread;

import com.message.IBusinessMessage;

/**
 * @author tryingpfq
 * @date 2018/11/23 12:01
 * 当在主线程中不好做的业务
 *  额外开启新的线程处理业务
 */
public interface IBussinessService extends Runnable {

    /** 线程名 **/
    public String getName();

    /** 启动 **/
    void start();

    /** 停止服务 **/
    void stop() throws InterruptedException;

    /** 服务启动的线程 **/
    Thread getThread();

    /** 往队列中添加消息 **/
    boolean pushMessage(IBusinessMessage message);


}
