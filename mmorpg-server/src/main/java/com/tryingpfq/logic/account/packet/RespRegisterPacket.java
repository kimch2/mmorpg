package com.tryingpfq.logic.account.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @Author Tryingpfq
 * @Time 2018/11/19 22:42
 */
@Packet(value = PacketId.RESP_REGISTER,description = "返回注册结果")
public class RespRegisterPacket extends AbstractPacket {
    @Protobuf(description = "注册结果 0:失败,1:成功")
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public short getPacketId() {
        return PacketId.RESP_REGISTER;
    }
}
