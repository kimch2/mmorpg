package com.tryingpfq.logic.fightteam.entity;

import com.tryingpfq.common.domain.enums.RoleType;
import com.tryingpfq.dao.entity.IEntity;
import com.tryingpfq.logic.fightteam.FightTeamJobType;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:22
 */
public class TeamMemberEntity implements IEntity<Long> {
    private long pid;

    private long teamId;

    private String pName;

    private RoleType roleType;

    private FightTeamJobType teamJobType;

    private int fightForce;

    private long enterTime;


    //
    public boolean isLeader(){
        return teamJobType.getType() == FightTeamJobType.LEADER.getType();
    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long aLong) {

    }

    public long getPid() {
        return pid;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getpName() {
        return pName;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public FightTeamJobType getTeamJobType() {
        return teamJobType;
    }

    public int getFightForce() {
        return fightForce;
    }

    public long getEnterTime() {
        return enterTime;
    }
}
