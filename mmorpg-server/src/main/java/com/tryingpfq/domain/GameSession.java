package com.tryingpfq.domain;

import com.net.codec.Response;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class GameSession {
    private static final Logger logger = LoggerFactory.getLogger(GameSession.class);

    private Channel channel;

    private String account = "";

    private String account_server;

    private long uId;  //用户ID

    private Player player;

    private GameSessionStatus status = GameSessionStatus.INIT;

    private String ip;

    private String server;

    public static Logger getLogger() {
        return logger;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount_server() {
        return account_server;
    }

    public void setAccount_server(String account_server) {
        this.account_server = account_server;
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public GameSessionStatus getStatus() {
        return status;
    }

    public void setStatus(GameSessionStatus status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void sendPacket(Response packet) {
        if(packet == null){
            return ;
        }
/*      Response response = packet.write()
        if(response == null){
            return;
        }
        write(response)*/
    }

    public boolean write(Response response){
        if(channel instanceof NullChannel){
            return false;
        }
        if(response != null){
            channel.writeAndFlush(response);
            return true;
        }
        return false;
    }

    private void scheduleFlush(final ChannelFuture writeFuture){
        final ScheduledFuture<?> sf = channel.eventLoop().schedule(new Runnable() {
            public void run() {
                if(!writeFuture.isDone()){
                    channel.flush();
                }
            }
        },30,TimeUnit.MILLISECONDS);

        writeFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                sf.cancel(false);
            }
        });
    }
}
