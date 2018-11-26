package com.tryingpfq.dao.entity;

import javax.persistence.MappedSuperclass;

/**
 * @author tryingpfq
 * @date 2018/11/26 11:44
 */
@MappedSuperclass
public interface IEntity<ID> {
    ID getId();

    void setId(ID id);
}
