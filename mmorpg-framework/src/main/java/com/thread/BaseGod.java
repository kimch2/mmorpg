package com.thread;

public interface BaseGod extends Runnable {

    public void stop();

    /** 执行前必须初始化，才能执行execute方法 **/
    public void init();

}
