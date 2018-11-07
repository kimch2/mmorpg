package com.tryingpfq.common.utils;

import java.util.Calendar;
/**
 * Created by tryingpfq on 2018/10/25.
 */
public class TimeUtils {
    public static final long TimeMillisOneSecond = 1000;
    public static final long TimeMillisThreeSecond = 3 * 1000;

    public static final long TimeMillisOneMinute = 60 * 1000;
    public static final long TimeMillisOneHour = 60 * 60 * 1000;
    public static final long TimeMillisOneDay = 24 * 60 * 60 * 1000;

    public static final long TimeMillisOneWeek = 7 * TimeMillisOneDay;

    public static final int secondOneDay = 24 * 60 * 60;


    public final static long getCurrentMillisTime(){
        return System.currentTimeMillis();
    }

    /**
     *获取零点时间戳
     */

    public final static int getMorningTime(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return (int)cal.getTimeInMillis() / 1000;
    }
    public final static long getMorningTimeMillis(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY,0);
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        cal.set(Calendar.MILLISECOND,0);
        return cal.getTimeInMillis();
    }
    public final static long getMidNightTimeMillis() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTimeInMillis();
    }

    /**
     *获取第二天零点时间戳
     */
    public final static long getTomorrowMorningMillisTime(){
        return getMorningTime() + secondOneDay;
    }
}
