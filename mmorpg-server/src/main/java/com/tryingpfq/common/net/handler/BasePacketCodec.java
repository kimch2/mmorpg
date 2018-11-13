package com.tryingpfq.common.net.handler;

/**
 * @author tryingpfq
 * @date 2018/11/13 14:56
 */

import com.baidu.bjf.remoting.protobuf.Codec;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

/**
 *  --4字节--   -2字节-  ------data------
 *   协议长度   协议id      协议数据
 */
public class BasePacketCodec extends ByteToMessageCodec<AbstractPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, AbstractPacket packet, ByteBuf out) throws Exception {
        if(packet == null)
            return;
        int len = 6;
        try{
            short packetId = packet.getPacketId();
            Codec codec = PacketId.getCodec(packetId);
            if(codec == null){
                throw new RuntimeException("协议Id不存在");
            }
            byte[] data = codec.encode(packet);
            len += data.length;
            out.writeInt(len);
            out.writeShort(packetId);
            out.writeBytes(data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) throws Exception {
        int len = in.readInt();
        if(in.readableBytes() < len -4){
            in.readableBytes();
            return;
        }
        short packetId = in.readShort();
        byte[] data =  new byte[len - 6];
        in.readBytes(data);
        Codec codec = PacketId.getCodec(packetId);
        if(codec == null){
            throw new RuntimeException("packet不存在");
        }
        Object decode = codec.decode(data);
        out.add(decode);
    }
}
