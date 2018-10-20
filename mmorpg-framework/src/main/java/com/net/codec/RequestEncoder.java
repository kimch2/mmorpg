package com.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by trying on 2018/10/20.
 */
public class RequestEncoder extends MessageToByteEncoder{
    private static int FLAG = -32523523;

    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        Request request = (Request) msg;
        //包头
        out.writeInt(FLAG);
        //模块
        out.writeShort(request.getModul());
        //命令
        out.writeShort(request.getCmd());
        //数据长度
        out.writeInt(request.getDataLength());
        if(request.getData() != null){
            out.writeBytes(request.getData());
        }
    }
}
