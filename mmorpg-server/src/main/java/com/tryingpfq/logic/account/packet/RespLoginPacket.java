package com.tryingpfq.logic.account.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @author tryingpfq
 * @date 2018/11/7 16:19
 */
@Packet(value = PacketId.RESP_LOGIN,description = "返回登录结果")
public class RespLoginPacket extends AbstractPacket {
    @Protobuf(description = "登陆结果")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public short getPacketId() {
        return PacketId.RESP_LOGIN;
    }
}
