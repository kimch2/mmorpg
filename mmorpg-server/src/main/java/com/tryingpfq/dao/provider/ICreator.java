package com.tryingpfq.dao.provider;

/**
 * @author tryingpfq
 * @date 2018/11/28 16:22
 */
public interface ICreator<T,ID> {
    T create(ID id);
}
