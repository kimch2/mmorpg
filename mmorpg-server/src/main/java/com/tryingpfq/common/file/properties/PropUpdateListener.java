package com.tryingpfq.common.file.properties;

/**
 * @author tryingpfq
 * @date 2018/12/27 15:22
 * Bean 实现这个接口的话， 可以对bean数据变化进行监听
 */
public interface PropUpdateListener<T> {
    void afterUpdate(T oldVal,ProConfig proConfig);
}
