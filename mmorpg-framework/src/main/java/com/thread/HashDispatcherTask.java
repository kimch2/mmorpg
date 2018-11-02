package com.thread;

/**
 * @author tryingpfq
 * @date 2018/11/2 10:06
 */
public abstract class HashDispatcherTask implements BaseDispatcherTask {

    public abstract String getDispatcherStr();

    @Override
    public int getDispatcherCode() {
        /**
         * >>> 无符号右移，忽略符号位 空位都以0补齐
         */
        int hashDisTaskCode = getDispatcherStr().hashCode() >>> 15 & getDispatcherStr().hashCode();
        return hashDisTaskCode;
    }
}
