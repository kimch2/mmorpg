package com.tryingpfq.logic.fightteam.entity;

import com.tryingpfq.dao.entity.IEntity;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:22
 */
public class TeamApplyEntity implements IEntity<Long> {
    private long pid;

    private long teamId;

    private byte jobType;

    private int level;

    private String pName;

    private long fightForce;

    private long applyTime;


    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long aLong) {

    }
}
