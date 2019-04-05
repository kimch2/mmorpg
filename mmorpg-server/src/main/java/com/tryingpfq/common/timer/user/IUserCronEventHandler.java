package com.tryingpfq.common.timer.user;

import com.tryingpfq.logic.player.Player;

/**
 * @author tryingpfq
 * @date 2019/1/17 15:11
 * 用户定时器接口
 */
public interface IUserCronEventHandler {

    String getHanldrName();

    void handler(Player player);

    /**
     * cron表达式
     *  0 0 * * *
     */
    String getCron();

}
