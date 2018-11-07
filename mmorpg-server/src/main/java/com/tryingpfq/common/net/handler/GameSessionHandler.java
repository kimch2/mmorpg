package com.tryingpfq.common.net.handler;

import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.common.domain.utils.ChannelUtils;
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
