package com.tryingpfq.common.domain;

import com.net.codec.Response;
import com.tryingpfq.common.domain.enums.CloseCause;
import com.tryingpfq.logic.player.Player;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GameSession implements Session{
    private static final Logger logger = LoggerFactory.getLogger(GameSession.class);

    private Channel channel;

    private String account = "";

    private String account_server;

    private long uId;  //用户ID

    private int id;

    private int key;

    private Player player;

    private GameSessionStatus status = GameSessionStatus.INIT;

    private String ip;

    private String server;

    private boolean registered;

    private static final AtomicInteger SEQ = new AtomicInteger(1);

    public static Logger getLogger() {
        return logger;
    }

    public static Session valueOf(Channel channel){
        GameSession gameSession = new GameSession();
        gameSession.id = SEQ.getAndIncrement();
        gameSession.channel = channel;
        return gameSession;
    }

    public Channel getChannel() {
        return channel;
    }

    public int getId() {
        return id;
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

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isRegistered(){
        return registered;
    }

    public void setRegistered(){
        this.registered = true;
    }
    public String getAsKey(){
        return account_server;
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

    public void close(CloseCause cause, String... ip){
        try{
            if(channel.isOpen()){
                channel.close();
                logger.info("CloseSession[{}] due to Cause[{}] {}",getAccount(),cause,ip);
            }else{
                logger.info("AlreadyCloseSession[{}] due to Cause[{}] {}",getAccount(),cause,ip);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
