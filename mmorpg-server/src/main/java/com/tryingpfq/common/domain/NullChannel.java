package com.tryingpfq.common.domain;

import io.netty.channel.*;

import java.net.SocketAddress;

public class NullChannel extends AbstractChannel {
    protected NullChannel(Channel parent) {
        super(parent);
    }

    protected AbstractUnsafe newUnsafe() {
        return null;
    }

    protected boolean isCompatible(EventLoop eventLoop) {
        return false;
    }

    protected SocketAddress localAddress0() {
        return null;
    }

    protected SocketAddress remoteAddress0() {
        return null;
    }

    protected void doBind(SocketAddress socketAddress) throws Exception {

    }

    protected void doDisconnect() throws Exception {

    }

    protected void doClose() throws Exception {

    }

    protected void doBeginRead() throws Exception {

    }

    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {

    }

    public ChannelConfig config() {
        return null;
    }

    public boolean isOpen() {
        return false;
    }

    public boolean isActive() {
        return false;
    }

    public ChannelMetadata metadata() {
        return null;
    }
}
