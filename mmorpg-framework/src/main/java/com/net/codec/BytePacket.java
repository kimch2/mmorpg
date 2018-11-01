package com.net.codec;

/**
 * @author tryingpfq
 * @date 2018/11/1 16:57
 */
public class BytePacket {
    private short packetId;

    private byte[] data;

    public void init(short packetId,byte[] data){
        this.packetId = packetId;
        this.data =data;
    }

    public short getPacketId() {
        return packetId;
    }

    public void setPacketId(short packetId) {
        this.packetId = packetId;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
