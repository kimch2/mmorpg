package com.tryingpfq.dao.provider;

/**
 * @author tryingpfq
 * @date 2018/11/26 11:47
 */
public interface IDataProvider<T,ID> {
    T get(ID id);
}
