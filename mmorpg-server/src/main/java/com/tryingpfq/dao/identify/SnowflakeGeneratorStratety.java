package com.tryingpfq.dao.identify;

import com.tryingpfq.common.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tryingpfq
 * @date 2018/12/21 10:38
 * ID 生成器 Twitter雪花算法
 */
@Component
public class SnowflakeGeneratorStratety implements GeneratorStrategy<Long> {
    private final long twepoch = 1545365355840L;

    //服务Id位数
    private final long serverIdBits = 12L;

    //最大服务Id
    private final long maxServerId = -1L ^ (-1L << serverIdBits);

    //序列号位数
    private final long sequenceBits = 10L;

    //最大序列号ID
    private final long maxSequence = -1L ^ (-1L << sequenceBits);

    private final long serverIdShift = serverIdBits;

    private final long timestampLeftShift = sequenceBits + serverIdBits;

    private long sequence = 0L;

    private long lastTimestamp = -1L;

    @Value("${serverId}")
    private long serverId;
    //重入锁
    private Lock lock = new ReentrantLock();

    public void init(){
        if ((serverId = serverId - 1000) > maxServerId) {
            throw new RuntimeException(String.format("serverId不能超过最大值%s", maxServerId));
        }
    }


    @Override
    public Long getGeneratorKey() {
        long timestamp = TimeUtils.getCurrentMillisTime();
        lock.lock();
        try{
            if(timestamp < lastTimestamp){
                throw new RuntimeException(String.format("时间被调整,上次时间戳 %d>%d", lastTimestamp, timestamp));
            }
            //在同一毫秒
            if(lastTimestamp == timestamp){
                sequence = (sequence + 1) & maxSequence;
                if(sequence == 0){
                    //自旋到下一秒
                    timestamp = tilNextMillis(lastTimestamp);
                }
            }else{
                sequence = 0L;
            }
            lastTimestamp = timestamp;
            return  ((timestamp - twepoch) << timestampLeftShift) | (serverId << serverIdShift) | sequence;
        }finally {
            lock.unlock();
        }
    }
    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = TimeUtils.getCurrentMillisTime();
        while (timestamp <= lastTimestamp) {
            timestamp = TimeUtils.getCurrentMillisTime();
        }
        return timestamp;
    }
    @Override
    public String getGeneratorType() {
        return "snowflakeid";
    }
}
