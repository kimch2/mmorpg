package com.tryingpfq.logic.fightteam.entity;

import com.tryingpfq.dao.entity.IEntity;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:22
 */
public class TeamMemberEntity implements IEntity<Long> {
    private long pid;

    private long teamId;

    private String pName;

    private byte jobType;

    private int fightForce;

    private long enterTime;



    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long aLong) {

    }
}
