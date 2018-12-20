package com.tryingpfq.logic.player.handler;

import com.annotation.WsController;
import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.logic.player.packet.ReqCreateRolePacket;
import com.tryingpfq.logic.player.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tryingpfq
 * @date 2018/12/20 16:29
 */
@WsController
public class PlayerHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerHandler.class);

    @Autowired
    private PlayerService playerService;

    public void createRoel(GameSession session, ReqCreateRolePacket packet){

    }
}
