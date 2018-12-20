package com.tryingpfq.logic.map.obj;

/**
 * @author tryingpfq
 * @date 2018/12/20 16:03
 */
public enum ObjectType {
    NONE(0),
    /**
     * 玩家
     */
    PLAYER(1),

    /**
     * 怪物
     */
    MONSTER(2),

    /**
     * NPC
     */
    NPC(3);


    private byte code;

    private ObjectType(int code){
        this.code = (byte)code;
    }

    public byte getCode() {
        return code;
    }

    public static ObjectType getById(int id){
        for(ObjectType type : ObjectType.values()){
            if(type.getCode() == id){
                return type;
            }
        }
        return NONE;
    }
}
