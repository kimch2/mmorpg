package com.tryingpfq.logic.fightteam.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @author tryingpfq
 * @date 2019/6/28 14:57
 */
@Packet(value = PacketId.REQ_APPLY_JOIN_FIGHT_TEAM, description = "申请加入战队")
public class ReqApplyJoinFightTeamPacket extends AbstractPacket {
    @Protobuf(description = "战队ID")
    private long teamId;

    @Override
    public short getPacketId() {
        return PacketId.REQ_APPLY_JOIN_FIGHT_TEAM;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
