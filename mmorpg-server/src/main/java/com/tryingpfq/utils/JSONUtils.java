package com.tryingpfq.utils;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * Created by tryingpfq on 2018/10/25.
 */
public class JSONUtils {

    private final static String NULL_JSON_OBJECT_STR = "{}";

    private final static String PRO_SPLIT = ",";

    private final static char KV_SPLIT = ':';
    private final static char OBJ_START = '{';
    private final static char OBJ_END = '}';

    /**
     * 参数为null或者空字符串返回true
     */
    public static boolean isNullJsonString(String jsonStr){
        return jsonStr == null || "".equals(jsonStr.trim());
    }

    /**
     * 是否为空串
     */
    public static boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }

    /**
     * 把一个Map转成JSON字符串
     */
    public static String toJsonString(Map<?,?> map){
        if(map == null || map.isEmpty()){
            return NULL_JSON_OBJECT_STR;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(OBJ_START);
        for(Map.Entry<?,?> entry : map.entrySet()){
            sb.append('"');
            sb.append(entry.getKey().toString());
            sb.append('"');
            sb.append(KV_SPLIT);
            sb.append('"');
            sb.append(entry.getValue().toString());
            sb.append('"');
            sb.append(PRO_SPLIT);
        }
        return sb.substring(0,sb.lastIndexOf(PRO_SPLIT)) + OBJ_END;
    }

    /**
     * 转成Integer集合
     */
    public static List<Integer> jsonToIntegerList(String json){
        if(JSONUtils.isNullJsonString(json)){
            return Collections.emptyList();
        }
        JSONArray array = JSONArray.fromObject(json.trim());
        List<Integer> list = new ArrayList<Integer>(array.size());
        for(int i = 0; i< array.size() ; i ++){
            list.add(array.getInt(i));
        }
        return list;
    }

    public static List<Long> jsonToLongList(String json){
        return toObject(json,TypeReference.LongList);
    }

    public static<T> T toObject(String json,Type type){
        return JSON.parseObject(json,type);
    }

    public static Map<Integer,Integer> mapFromJsonInteger(String json){
        json = StringUtils.trimToNull(json);
        if(StringUtils.isBlank(json)){
            return Collections.emptyMap();
        }
        json = replace(json);
        return toObject(json,TypeReference.Integer2IntegerMap);
    }

    private static String replace(String json){
        json = StringUtils.replace(json,"，",",");
        json = StringUtils.replace(json,"：",":");
        json = StringUtils.replace(json,"；",";");
        return json;
    }
}


