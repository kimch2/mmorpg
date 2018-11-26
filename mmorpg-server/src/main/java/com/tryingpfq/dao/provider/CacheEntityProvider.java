package com.tryingpfq.dao.provider;

import com.tryingpfq.dao.entity.IEntity;

import java.io.Serializable;

/**
 * hibernate + 缓存（caffeine）
 * @author tryingpfq
 * @date 2018/11/26 20:52
 */
public class CacheEntityProvider<T extends IEntity<ID>,ID extends Serializable> extends HibernateEntityProvider<T,ID>{

}
