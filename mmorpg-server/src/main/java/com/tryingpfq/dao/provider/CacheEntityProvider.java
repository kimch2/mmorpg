package com.tryingpfq.dao.provider;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.tryingpfq.dao.entity.IEntity;
import com.tryingpfq.dao.exception.GeneratorException;
import com.tryingpfq.dao.identify.GeneratorStrategyIdentify;
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
        try {
            ID id = (ID)GeneratorStrategyIdentify.getInstance().getGeneratorStrategy(getKeyGenerator()).getGeneratorKey();
            entity.setId(id);
        }catch (Exception e){
            throw  new GeneratorException("策略生成的id和实体id不一致");
        }
        cache.put(entity.getId(),entity);
    }

    @Override
    public void update(T entity) {
        cache.put(entity.getId(),entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        cache.put(entity.getId(),entity);
    }

    @Override
    public T loadAndCreate(ID id, ICreator<T, ID> creator) {
        T t = get(id);

        if(t != null){
            return t;
        }else{
            t = creator.create(id);
            save(t);
        }
        return t;
    }

    @Override
    public void delete(ID id) {
        cache.invalidate(id);
    }

    @Override
    public void delete(T entity) {
        cache.invalidate(entity.getId());
    }
}
