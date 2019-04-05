package com.tryingpfq.logic.player.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.domain.enums.RoleType;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @author tryingpfq
 * @date 2018/12/20 17:11
 */
@Packet(value=PacketId.REQ_CREATE_ROLE,description = "请求创建新的角色")
public class ReqCreateRolePacket extends AbstractPacket {
    @Protobuf(description = "角色名")
    private String roleName;
    @Protobuf(description = "性别")
    private int sex;
    @Protobuf(description = "职业")
    private RoleType roleType;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public short getPacketId() {
        return PacketId.REQ_CREATE_ROLE;
    }
}
