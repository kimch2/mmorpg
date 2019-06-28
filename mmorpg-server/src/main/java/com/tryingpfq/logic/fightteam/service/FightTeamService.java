package com.tryingpfq.logic.fightteam.service;

import com.tryingpfq.logic.fightteam.entity.FightTeamEntity;
import com.tryingpfq.logic.fightteam.manager.FightTeamManager;
import com.tryingpfq.logic.player.Player;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:41
 *
 *  战队系统 只是作为一个简单的战队系统 teamId 并没有与玩家关联
 */
@Component
public class FightTeamService {

    @Autowired
    private FightTeamManager fightTeamManager;

    public void create(Player player,String name){
        //checkCondition

        String tName = StringUtils.trimToEmpty(name);

        //isRegister
        if (fightTeamManager.registerName(name)) {
            //提示
            return;
        }
        fightTeamManager.create(player,name);

    }

    public boolean applyjoinTeam(Player player,long teamId){
        FightTeamEntity teamEntity = fightTeamManager.getTeamEntityById(teamId);
        if (teamEntity == null) {
            return false;
        }
        //判断玩家是否在战队中
        if (fightTeamManager.getTeamEntityByPlayerId(player.getId()) == null) {
            return false;
        }
        return true;
    }
}
