package com.tryingpfq.logic.fightteam.manager;

import com.google.common.collect.Sets;
import com.tryingpfq.dao.provider.BaseEntityProvider;
import com.tryingpfq.logic.fightteam.entity.FightTeamEntity;
import com.tryingpfq.logic.fightteam.entity.TeamMemberEntity;
import com.tryingpfq.logic.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:41
 */
@Component
public class FightTeamManager {

    @Autowired
    private BaseEntityProvider<FightTeamEntity,Long> fightTeamEntityProvider;

    @Autowired
    private BaseEntityProvider<TeamMemberEntity,Long> teamMemberEntityProvider;

    private Set<String> registerName = Sets.newConcurrentHashSet();

    public FightTeamEntity create(Player player,String name){
        FightTeamEntity fightTeamEntity = new FightTeamEntity(0L,player.getId(),name);
        fightTeamEntityProvider.save(fightTeamEntity);
        return fightTeamEntity;
    }

    public boolean registerName(String name) {
        return registerName.add(name);
    }
}
