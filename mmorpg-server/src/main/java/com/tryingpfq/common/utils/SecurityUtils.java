package com.tryingpfq.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * @author tryingpfq
 * @date 2018/12/28 20:09
 */
public class SecurityUtils {

    public static String getMd5Code(String str){
        try{
            //生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1,md.digest()).toString(16);
        }catch (Exception e){
            throw new RuntimeException("Md5加密失败");
        }
    }
}
