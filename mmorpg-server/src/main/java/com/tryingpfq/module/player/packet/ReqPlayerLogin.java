package com.tryingpfq.module.player.packet;


import com.serial.Serializer;

public class ReqPlayerLogin extends Serializer{

    private long playerId;

    private String pass;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    protected void read() {
        this.playerId = readLong();
        this.pass = readString();
    }

    protected void write() {
        writeLong(this.getPlayerId());
        writeString(this.getPass());
    }
}
