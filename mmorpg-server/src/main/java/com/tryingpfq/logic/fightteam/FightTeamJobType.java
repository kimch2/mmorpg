package com.tryingpfq.logic.fightteam;

/**
 * @author tryingpfq
 * @date 2019/6/17 17:05
 */
public enum  FightTeamJobType {
    LEADER(1,"队长"),

    MEMBER(2,"成员"),
    ;

    private byte type;

    private String desc;

    FightTeamJobType(int type,String desc){
        this.type = (byte) type;
        this.desc = desc;
    }

    public byte getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public static FightTeamJobType getByType(byte type) {
        for (FightTeamJobType jobType : values()) {
            if (jobType.getType() == type) {
                return jobType;
            }
        }
        throw new RuntimeException("no fightTeamJobType " + type);
    }
}
