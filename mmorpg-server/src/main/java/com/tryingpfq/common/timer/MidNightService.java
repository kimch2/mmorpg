package com.tryingpfq.common.timer;

import com.google.common.collect.Lists;
import com.tryingpfq.logic.OnlinePlayer;
import com.tryingpfq.logic.base.rpc.IPlayerMessage;
import com.tryingpfq.logic.player.Player;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2019/3/12 17:07
 */
@Component
public class MidNightService implements IMidNighterService{


    @Override
    public void zero() {
        List<Player> allOnlinePlayer = Lists.newArrayList();    //deal
        for(Player player : allOnlinePlayer){
            player.addPlayerMessage(new IPlayerMessage() {
                @Override
                public void execute(Player cPlayer) {
                    playerOnZero(cPlayer);
                }
            });
        }
    }

    @Override
    public void playerOnZero(Player player) {

    }
}
