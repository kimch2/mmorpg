package com.tryingpfq.logic.fightteam.handler;

import com.annotation.WsController;
import com.annotation.WsRequest;
import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.logic.fightteam.packet.ReqApplyJoinFightTeamPacket;
import com.tryingpfq.logic.fightteam.packet.ReqDealTeamApplyPacket;
import com.tryingpfq.logic.fightteam.packet.ReqFightTeamCreatePacket;
import com.tryingpfq.logic.fightteam.service.FightTeamService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:41
 */
@WsController
public class FightTeamHandler {
    @Autowired
    private FightTeamService fightTeamService;

    @WsRequest
    public void createTeam(GameSession session, ReqFightTeamCreatePacket packet) {
        fightTeamService.create(session.getPlayer(),packet.getName());
    }

    public void applyTeam(GameSession gameSession, ReqApplyJoinFightTeamPacket packet) {
        if(fightTeamService.applyjoinTeam(gameSession.getPlayer(),packet.getTeamId())){

        }else{

        }
    }

    public void dealApply(GameSession session, ReqDealTeamApplyPacket packet) {

    }

}
