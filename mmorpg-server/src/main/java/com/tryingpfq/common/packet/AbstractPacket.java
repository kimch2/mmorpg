package com.tryingpfq.common.packet;

import com.tryingpfq.common.utils.ProtoFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
/**
 * @author tryingpfq
 * @date 2018/11/1
 * |  --4字节--  | -2字节- |-------data-------|
 *  协议长度     协议id     议数据
 */
public abstract class AbstractPacket {
    @Autowired
    private PacketId packetId;

    public abstract short getPacketId();

    //TODO 不知道为什么这个方法不执行
    @PostConstruct
    protected void dd(){
        ProtoFileUtils.createProtoFile(this);
        packetId.registerPacketId2AbstractPacket(this);
        packetId.registerPacketId2Codec(this);
    }
}
