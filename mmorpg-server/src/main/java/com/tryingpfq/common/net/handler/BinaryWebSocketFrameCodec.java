package com.tryingpfq.common.net.handler;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.google.common.base.Preconditions;
import com.net.codec.BytePacket;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/11/1 16:28
 * 编 / 解码器
 */
public class BinaryWebSocketFrameCodec extends MessageToMessageCodec<BinaryWebSocketFrame,AbstractPacket> {
    private static final Logger logger = LoggerFactory.getLogger(BinaryWebSocketFrameCodec.class);

    protected void encode(ChannelHandlerContext ctx, AbstractPacket packet, List<Object> out) throws Exception {
        /**
         * 编码
         * 数据格式
         *包长度(4 bit)  + packetId(2 bit) + 数据
         */
        if(packet == null){
            return;
        }
        short packetId = packet.getPacketId();
        Codec codec = PacketId.getCodec(packetId);
        Preconditions.checkNotNull(codec, "packetId对应的编码器为空...");
        int length = 6;
        ByteBuf bytebuf = Unpooled.buffer(64);
        try{
            byte[] data = codec.encode(packet);
            length += data.length;
            bytebuf.writeInt(length);
            bytebuf.writeShort(packetId);
            bytebuf.writeBytes(data);
            BinaryWebSocketFrame bWebSocketFrame = new BinaryWebSocketFrame(bytebuf);
            out.add(bWebSocketFrame);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    protected void decode(ChannelHandlerContext ctx, BinaryWebSocketFrame msg, List<Object> list) throws Exception {
        /**
         * 解码
         * 数据格式
         *包长度(4 bit)  + packetId(2 bit) + 数据
         */
        ByteBuf in = null;
        if(msg instanceof BinaryWebSocketFrame){
            in = msg.content();  //二进制
        }else{
            throw new RuntimeException("msg is not BinaryWebSocketFrame");
        }
        int length = in.readInt();
        if(in.readableBytes() != (length - 4)){
            in.readableBytes();
            logger.error("数据包缺失");
            return;
        }

        short packetId = in.readShort();
        byte[] data = new byte[length -6];
        in.readBytes(data);
        BytePacket packet = new BytePacket();
        packet.init(packetId,data);
        list.add(packet);
    }
}
