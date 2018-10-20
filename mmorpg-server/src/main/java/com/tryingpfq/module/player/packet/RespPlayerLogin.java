package com.tryingpfq.module.player.packet;

import com.serial.Serializer;

public class RespPlayerLogin extends Serializer {

    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    protected void read() {
        this.money = readInt();
    }

    protected void write() {
        writeInt(money);
    }
}
