package com.tryingpfq.common.file.properties;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:45
 */
public interface Prop<T> {

    void update(T t);

    ProConfig getConfig();

    /**
     * 返回代理对象
     */
    T original();
}
