package com.tryingpfq.logic.player.entity;

import com.tryingpfq.common.domain.enums.RoleType;
/**
 * Created by trying on 2018/10/26.
*/
public class PlayerEntity {

    private long playerId;    //ID
    private String account;     //账户
    private String server;
    private String name;    //昵称
    private RoleType roleType;   //职业
    private byte gender;        //性别
    private int scene;          //当前场景
    private int x;              //坐标X
    private int y;              //坐标Y
    private int level;          //等级
    private long glod;            //元宝

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

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
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

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
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
}
