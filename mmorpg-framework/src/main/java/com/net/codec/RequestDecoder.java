package com.net.codec;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by trying on 2018/10/20.
 * /**
 * 请求解码器
 * <pre>
 * 数据包格式
 * 包头 + 模块号 + 命令号 + 长度 + 数据
 * </pre>
 * 包头4字节
 * 模块号2字节short
 * 命令号2字节short
 * 长度4字节(描述数据部分字节长度)
 *
 */

public class RequestDecoder extends ByteToMessageDecoder{
    /**
     *数据包基本长度
     */
    private static int BASE_LENGTH = 4 + 2 + 2 + 4;

    //包头
    private static int FLAG = -32523523;

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //可读长度必须大于基本长度
        if(in.readableBytes() >= BASE_LENGTH){
            //防止socket字节流攻击
            if(in.readableBytes() > 2048){
                in.skipBytes(in.readableBytes());  //跳过
            }
            //记录包头开始的index
            int beginReader;

            while (true){
                beginReader = in.readerIndex();
                in.markReaderIndex();

                if(in.readInt() == FLAG){
                    break;
                }

                //未读到包头的时候 略过一个字节
                in.resetReaderIndex();
                in.readByte();

                if(in.readableBytes() < BASE_LENGTH){
                    return;
                }
            }
            //模块号
            short module = in.readShort();

            //命令号
            short cmd = in.readShort();
            //长度
            int length = in.readInt();

            //判断请求数据包数据是否到齐
            if(in.readableBytes() < length){
                //还原指针
                in.readerIndex(beginReader);
                return;
            }

            //判读data数据
            byte[] data = new byte[length];
            in.readBytes(data);

            Request request = new Request();
            request.setModul(module);
            request.setCmd(cmd);
            request.setData(data);

            //继续往下传递
            out.add(request);
        }
    }
}
