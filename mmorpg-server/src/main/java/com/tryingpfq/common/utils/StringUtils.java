package com.tryingpfq.common.utils;

import java.nio.charset.Charset;
/**
 * Created by tryingpfq on 2018/10/25.
 */

public class StringUtils {
    /** 分隔符 **/
    private final static String COMMA = ",";

    /** 分隔符 **/
    private final static char COMMA_CHAR = ',';

    public final static Charset UTF_8 = Charset.forName("utf-8");

    public final static String EMPTY = org.apache.commons.lang3.StringUtils.EMPTY;

    public static String notNull(String str){
        return str == null ? "" : str;
    }

    public static boolean isBlank(String str){
        return org.apache.commons.lang3.StringUtils.isBlank(str);
    }

    /**
     * 空白字符串 空字符 null 字符null 都为true
     */
    public static boolean isNull(String str){
        return isBlank(str) || "null".equals(str);
    }

    /**
     * 首字母小写
     */
    public static String antiCapitalize(String str){
        char charAt = str.charAt(0);
        if(charAt >= 'A' && charAt <= 'Z'){
            str = str.replaceFirst(String.valueOf(str.charAt(0)),String.valueOf((char )('a' - 'A' + charAt)));
        }
        return str;
    }

    /**
     * 首先字母大写
     */
    public static String capitalize(String str){
        char charAt = str.charAt(0);
        if(charAt >= 'a' && charAt <= 'z'){
            str = str.replaceFirst(String.valueOf(str.charAt(0)),String.valueOf((char )('A' - 'a' + charAt)));
        }
        return str;
    }

    /**
     *将字符串转为整型数组
     * @param param
     * @param split
     * @param expectLength  期望分割的长度
     * @return
     */
    public static int[] toIntArray(String param,String split,int expectLength){
        String[] ss = toStringArray(param,split);
        if(expectLength < 1){
            expectLength = ss.length;
        }
        int[] l = new int[expectLength];
        for(int i = 0;i<expectLength;i++){
            if(i < ss.length -1)
                l[i] = Integer.parseInt(ss[i].trim());
            else
                l[i] = 0;
        }
        return l;
    }

    /**
     * 将字符串 转为字符串数组
     * @param param
     * @param split
     * @return
     */
    public static String[] toStringArray(String param,String split){
        if(param == null || "".equals(param.trim())){
            return new String[0];
        }
        String[] split2 = org.apache.commons.lang3.StringUtils.split(param,split);
        for(int i= 0; i<split2.length ;i++){
            split2[i] = split2[i].trim();
        }
        return split2;
    }

    /**
     * 将int数转为字符串，并根据split分隔开
     * @param l
     * @param split
     * @return
     */
    public static String toString(int[] l,String split){
        String s = "";
        for(int i = 0;i<l.length;i++){
            s += l[i];
            if(i<l.length -1)
                s += split;
        }
        return s;
    }
}
