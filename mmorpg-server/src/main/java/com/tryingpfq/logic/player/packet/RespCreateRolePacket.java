package com.tryingpfq.logic.player.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @author tryingpfq
 * @date 2018/12/20 18:00
 */
@Packet(value = PacketId.RESP_CREATE_ROLE,description = "返回创建角色结果")
public class RespCreateRolePacket extends AbstractPacket {
    @Protobuf(description = "结果状态")
    private int result;

    @Protobuf(description = "角色id")
    private long playerId;

    @Protobuf(description = "创建的角色信息")
    private CreateRoleVo roleInfo;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public CreateRoleVo getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(CreateRoleVo roleInfo) {
        this.roleInfo = roleInfo;
    }

    @Override
    public short getPacketId() {
        return PacketId.RESP_CREATE_ROLE;
    }
}
