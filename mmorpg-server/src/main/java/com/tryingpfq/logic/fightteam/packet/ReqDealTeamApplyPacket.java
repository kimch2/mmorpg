package com.tryingpfq.logic.fightteam.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @author tryingpfq
 * @date 2019/6/28 16:56
 */
@Packet(value = PacketId.REQ_DEAL_TEAM_APPLY,description = "处理战队申请")
public class ReqDealTeamApplyPacket extends AbstractPacket {

    @Protobuf(description = "接受 or 拒绝")
    private byte result;

    @Override
    public short getPacketId() {
        return PacketId.REQ_DEAL_TEAM_APPLY;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }
}
