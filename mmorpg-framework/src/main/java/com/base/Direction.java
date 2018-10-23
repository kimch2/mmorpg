package com.base;

public enum Direction {
    TYPE_NORTH(0),
    TYPE_EAST_NORTH(1),
    TYPE_EAST(2),
    TYPE_EAST_SOUTH(3),
    TYPE_SOUTH(4),
    TYPE_WEST_SOUTH(5),
    TYPE_WEST(6),
    TYPE_WEST_NORTH(7);

    private int dir;

    Direction(int dir){
        this.dir = dir;
    }

    public int getDir(){
        return dir;
    }


}
