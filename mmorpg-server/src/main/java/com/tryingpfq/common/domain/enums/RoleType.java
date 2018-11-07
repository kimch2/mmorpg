package com.tryingpfq.common.domain.enums;

/**
 * 角色类型
 */
public enum RoleType {
    None(0,"无"),

    ZHAN_SHI(1,"战士"),

    FA_SHI(2,"法师"),

    DAO_SHI(3,"道士");

    private byte id;

    private String desc;

    private RoleType(int id,String desc){
        this.id = (byte)id;
        this.desc = desc;
    }

    public byte getId(){
        return id;
    }

    public String getDesc(){
        return desc;
    }
}
