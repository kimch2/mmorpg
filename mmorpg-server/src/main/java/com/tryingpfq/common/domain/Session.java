package com.tryingpfq.common.domain;

import io.netty.channel.Channel;

/**
 * @author tryingpfq
 * @date 2018/11/2 15:56
 */
public interface Session {
    public Channel getChannel();

    public int getId();
}
