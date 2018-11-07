package com.tryingpfq.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
/**
 * Created by tryingpfq on 2018/10/25.
 */
public class TypeReference<T> {
    private final Type type;


    protected TypeReference(){
        Type superClas = getClass().getGenericSuperclass();

        type = ((ParameterizedType) superClas) .getActualTypeArguments()[0];
    }

    public Type getType(){
        return type;
    }

    public final static Type LIST_STRING = new TypeReference<List<String>>() {}.getType();

    public final static Type LongList = new TypeReference<List<Long>>() {}.getType();

    public final static Type Integer2IntegerMap = new TypeReference<Map<Integer,Integer>>() {} .getType();
}
