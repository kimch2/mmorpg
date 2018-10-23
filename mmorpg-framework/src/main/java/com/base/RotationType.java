package com.base;

public enum RotationType {
    /**
     * 顺时针
     */
    Cloclwise{
        @Override
        public int calDir(int dir,int y){
            return dir + y;
        }
    },

    /**
     * 逆时针
     */
    Anticlockwise{
        @Override
        public int calDir(int dir, int y) {
            return dir - y;
        }
    }
    ;


    public abstract int calDir(int dir,int y);

}
