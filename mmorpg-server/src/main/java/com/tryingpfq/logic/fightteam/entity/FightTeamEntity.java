package com.tryingpfq.logic.fightteam.entity;

import com.tryingpfq.dao.entity.IEntity;

import javax.persistence.Id;
import java.util.List;

/**
 * @Author Tryingpfq
 * @Time 2019/5/26 22:14
 */
public class FightTeamEntity implements IEntity<Long> {

    @Id
    private long teamId;

    private long leaderId;

    private String tName;

    private long createTime;

    private List<TeamMemberEntity> memeberList;


    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long aLong) {

    }


}
