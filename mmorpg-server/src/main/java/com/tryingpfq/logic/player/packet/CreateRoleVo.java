package com.tryingpfq.logic.player.packet;

import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.logic.player.entity.PlayerEntity;

/**
 * @author tryingpfq
 * @date 2018/12/20 18:26
 */
public class CreateRoleVo {
    @Protobuf(description = "性别")
    private byte gender;

    @Protobuf(description = "name")
    private String name;

    @Protobuf(description = "职业")
    private String roleType;

    public static CreateRoleVo valueOf(PlayerEntity entity){
        CreateRoleVo vo = new CreateRoleVo();
        vo.setName(entity.getName());
        vo.setGender(vo.getGender());
        vo.setRoleType(vo.getRoleType());
        return vo;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
