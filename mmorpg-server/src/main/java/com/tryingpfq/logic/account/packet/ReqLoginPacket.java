package com.tryingpfq.logic.account.packet;

import com.annotation.Packet;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;

/**
 * @author tryingpfq
 * @date 2018/11/7 16:05
 */
@Packet(PacketId.REQ_LOGIN)
public class ReqLoginPacket extends AbstractPacket {
    @Protobuf(description = "账号")
    private String account;

    @Protobuf(description = "密码")
    private String psw;

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    @Override
    public short getPacketId() {
        return PacketId.REQ_LOGIN;
    }
}
