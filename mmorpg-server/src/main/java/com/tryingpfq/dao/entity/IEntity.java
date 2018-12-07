package com.tryingpfq.dao.entity;

import com.tryingpfq.dao.type.JsonType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.MappedSuperclass;

/**
 * @author tryingpfq
 * @date 2018/11/26 11:44
 */
@MappedSuperclass
@TypeDef(name = "json",typeClass = JsonType.class)
public interface IEntity<ID> {
    ID getId();

    void setId(ID id);
}
