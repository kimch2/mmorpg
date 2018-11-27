package com.tryingpfq.dao.provider;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tryingpfq.dao.entity.IEntity;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * hibernate + 缓存（caffeine）
 * @author tryingpfq
 * @date 2018/11/26 20:52
 */
public class CacheEntityProvider<T extends IEntity<ID>,ID extends Serializable> extends HibernateEntityProvider<T,ID>{
    private LoadingCache<ID,T> cache = Caffeine.newBuilder().maximumSize(1000)
                                        .expireAfterWrite(10,TimeUnit.MINUTES)
                                        .build(id -> super.get(id));

    @Override
    public T get(ID id) {
        //从缓存中获取
        T t = cache.get(id);
        return t;
    }

    @Override
    public void save(T entity) {

    }
}
