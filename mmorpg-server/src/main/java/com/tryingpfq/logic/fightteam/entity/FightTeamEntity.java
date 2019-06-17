package com.tryingpfq.logic.fightteam.entity;

import com.google.common.collect.Lists;
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

    private String tName;

    private long createTime;

    private TeamMemberEntity leader;

    private List<TeamMemberEntity> memeberList = Lists.newArrayList();


    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long aLong) {

    }

    /** logic **/
    public TeamMemberEntity getLeader(){
        if (leader != null) {
            return leader;
        }
        for (TeamMemberEntity member : memeberList) {
            if (member.isLeader()) {
                this.leader = member;
                return member;
            }
        }
        throw new RuntimeException("this team error : is not leader");
    }

}
