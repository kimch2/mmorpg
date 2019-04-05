package com.tryingpfq.common.timer.cronhandler;

import com.tryingpfq.common.timer.user.IUserCronEventHandler;
import com.tryingpfq.logic.player.Player;

import java.time.chrono.MinguoDate;

/**
 * @author tryingpfq
 * @date 2019/1/17 15:47
 */

public class MidNightHandler implements IUserCronEventHandler {
    @Override
    public String getHanldrName() {
        return MidNightHandler.class.getSimpleName();
    }

    @Override
    public void handler(Player player) {
        //do something
        //ListnerManager.getInstance().fireMidNightListener(player);
    }

    @Override
    public String getCron() {
        return "0 0 * * *";
    }
}
