package com.tryingpfq.dao.provider;

import com.tryingpfq.dao.entity.IEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/11/26 11:48
 */
@Repository
public interface BaseEntityProvider<T extends IEntity,ID> extends IDataProvider<T,ID> {
    T get(ID id);

    void save(T entity);

    void update(T entity);

    T loadAndCreate(ID id,ICreator<T,ID> creator);

    void saveOrUpdate(T entity);

    void delete(T entity);

    void delete(ID id);

    List<T> query(String sql,Object... params);

}
