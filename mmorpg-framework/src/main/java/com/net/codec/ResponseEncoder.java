package com.net.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by trying on 2018/10/20.
 */
public class ResponseEncoder extends MessageToByteEncoder{

    private static int FALG = -32523523;

    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        Response response = (Response) msg;

        //包头
        out.writeInt(FALG);
        //模块
        out.writeShort(response.getModule());
        //命令
        out.writeShort(response.getCmd());
        //状态
        out.writeInt(response.getStateCode());
        //长度
        out.writeInt(response.getDataLength());
        if(response.getData() != null){
            out.writeBytes(response.getData());
        }


    }
}
