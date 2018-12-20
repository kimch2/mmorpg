package com.tryingpfq.logic.player.entity;

import com.tryingpfq.common.domain.enums.RoleType;
import com.tryingpfq.dao.anno.IdGenerator;
import com.tryingpfq.dao.entity.IEntity;

import javax.persistence.*;

/**
 * Created by trying on 2018/10/26.
*/
@Entity
@NamedQueries({
        @NamedQuery(name="findPlayerByAccount",query = "select p from PlayerEntity p where p.account = ?1"),
        @NamedQuery(name="findPlayerById",query = "select p from PlayerEntity p where p.id = ?1"),
        @NamedQuery(name="findPlayerByName",query = "select p from PlayerEntity p where p.name = ?1")
})
public class PlayerEntity implements IEntity<Long> {
    @Id
    @Column
    @IdGenerator("snowflakeid")
    private long playerId;    //ID
    @Column
    private String account;     //账户
    //private String server;
    @Column
    private String name;    //昵称
    @Column
    private RoleType roleType;   //职业
    @Column
    private byte gender;        //性别
    @Column
    private int sceneId;          //当前场景
    @Column
    private int x;              //坐标X
    @Column
    private int y;              //坐标Y
    @Column
    private int level;          //等级
    @Column
    private int exp;            //当前等级对应的经验值
    @Column
    private long glod;            //元宝

    public PlayerEntity(){}

    public PlayerEntity(String account,String name,RoleType type,byte sex){
        this.account = account;
        this.name = name;
        this.roleType = type;
        this.gender = sex;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int scene) {
        this.sceneId = scene;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getGlod() {
        return glod;
    }

    public void setGlod(long glod) {
        this.glod = glod;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public Long getId() {
        return playerId;
    }

    @Override
    public void setId(Long id) {
        this.playerId = id;
    }
}
