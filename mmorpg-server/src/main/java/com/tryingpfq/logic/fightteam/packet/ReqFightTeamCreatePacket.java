package com.tryingpfq.logic.fightteam.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @author tryingpfq
 * @date 2019/6/17 17:23
 */
@Packet(value = PacketId.REQ_CREATE_FIGHT_TEAM, description = "创建战队")
public class ReqFightTeamCreatePacket extends AbstractPacket {
    @Protobuf(description = "战队名称")
    private String name;

    @Override
    public short getPacketId() {
        return PacketId.REQ_CREATE_FIGHT_TEAM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
