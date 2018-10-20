package com.net.handler;

import com.net.codec.Request;
import com.net.codec.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by trying on 2018/10/20.
 * 接收消息处理类
 */
@Component
public class ServerHandler extends SimpleChannelInboundHandler{
    private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    /**
     * 接收消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request message = (Request) msg;

        if(message.getModul() == 1){
            if(message.getCmd() == 1){
                //客服端发送过来的命令目标位置
                String context = new String(message.getData());

                Response response = new Response();
                response.setModule((short)1);
                response.setCmd((short)1);
                response.setStateCode(0);
                response.setData("成功进入场景".getBytes());
                ctx.channel().writeAndFlush(response);
                response.setData("场景内有 NPC 怪物 玩家等".getBytes());
                ctx.channel().writeAndFlush(response);
            }else if(message.getCmd() == 2){

            }

        }else if (message.getModul() != 1){


        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        logger.info("客户端连接进来");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelInactive();
        ctx.channel().writeAndFlush("close");
        logger.info("客服端端口连接");
    }
}
