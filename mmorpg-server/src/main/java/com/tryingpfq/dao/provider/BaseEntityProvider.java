package com.tryingpfq.dao.provider;

import com.tryingpfq.dao.entity.IEntity;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/11/26 11:48
 */
public interface BaseEntityProvider<T extends IEntity,ID> extends IDataProvider<T,ID> {
    T get(ID id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(ID id);

    List<T> query(String sql,Object... params);
}
