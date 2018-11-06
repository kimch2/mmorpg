package com.tryingpfq.net.handler;

import com.tryingpfq.domain.GameSession;
import com.tryingpfq.domain.utils.ChannelUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

/**
 * Created by trying on 2018/11/2.
 */
@Component
@ChannelHandler.Sharable
public class GameSessionHandler extends ChannelInboundHandlerAdapter{
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //添加回话
        ChannelUtils.addChannelSession(ctx.channel(), GameSession.valueOf(ctx.channel()));
        super.channelActive(ctx);
    }
}
