package com.tryingpfq.logic.account.entity;

import com.tryingpfq.dao.anno.IdGenerator;
import com.tryingpfq.dao.entity.IEntity;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/12/3 17:04
 */
@Entity
@NamedQueries({@NamedQuery(name = "findAccountByAccountAndPsw", query = "select account from AccountEntity account " +
        "where account.account =? and account.psw = ?")})
public class AccountEntity implements IEntity<String> {
    @Id
    @Column
    @IdGenerator
    private String id;

    @Column
    private String account;

    @Column
    private String psw;

    @Type(type = "json")
    private List<Integer> ids;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String s) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
