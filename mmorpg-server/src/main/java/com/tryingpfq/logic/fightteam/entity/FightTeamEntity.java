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

    private long pid;

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

    public FightTeamEntity(){

    }

    public FightTeamEntity(long teamId, long pid, String tName) {
        this.teamId = teamId;
        this.pid = pid;
        this.tName = tName;
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

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public void setLeader(TeamMemberEntity leader) {
        this.leader = leader;
    }

    public List<TeamMemberEntity> getMemeberList() {
        return memeberList;
    }

    public void setMemeberList(List<TeamMemberEntity> memeberList) {
        this.memeberList = memeberList;
    }
}
