package com.base;

/**
 * 点，一个点是不可变的，当执行完点里面的运算后，返回的是一个对象
 */
public class Point {
    private final short x;

    private final short y;

    private Point(short x,short y){
        this.x = x;
        this.y = y;
    }


}
