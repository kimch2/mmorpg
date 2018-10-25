package com.tryingpfq.utils;

import com.google.common.base.Preconditions;
import io.netty.util.internal.ThreadLocalRandom;
/**
 * Created by tryingpfq on 2018/10/25.
 */

public class RandomUtils {

    /**
     * 在[min,max）区间产生一个随机数
     * @param min
     * @param max
     * @return
     */
    public static int generateBetween(int min,int max){
        if(min == max)
            return min;
        Preconditions.checkArgument(min >= 0,"argument min[%s] should not be negative",min);
        Preconditions.checkArgument(min < max,"argument min[%s] should not lager than max[%s]",min,max);
        return ThreadLocalRandom.current().nextInt(max - min) + min;
    }

    /**
     * 在[min,max]中随机产生一个数
     * @param min
     * @param max
     * @return
     */
    public static int randomBothInclude(int min ,int max){
        if(min < 0)
            throw new IllegalArgumentException("argument min should not be positive");
        if(min > max)
            throw new IllegalArgumentException("argument min should not larger than max");
        if(min == max){
            return min;
        }
        return ThreadLocalRandom.current().nextInt(max - min +1) + min;
    }

    /**
     * 随机产生一个数
     */
    public static int nextInt(int i){
        return ThreadLocalRandom.current().nextInt(i);
    }

}
