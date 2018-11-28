package com.tryingpfq.dao.identify;

/**
 * @author tryingpfq
 * @date 2018/11/28 14:48
 */
public interface GeneratorStrategy<T> {
    T getGeneratorKey();

    String getGeneratorType();
}
