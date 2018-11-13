package com.tryingpfq.common.handler;

import com.alibaba.fastjson.JSON;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * 消息接受处理类
 *
 */
@Service
public class ClientHandler extends SimpleChannelInboundHandler {
	@Override
	protected void channelRead0(io.netty.channel.ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(JSON.toJSONString(msg));
	}
}
