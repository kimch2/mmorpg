package com.message;

/**
 * @author tryingpfq
 * @date 2018/11/23 11:54
 */
public interface IBusinessMessage {
    /**
     * 开启额外线程处理业务
     */
    public void execute();
}
