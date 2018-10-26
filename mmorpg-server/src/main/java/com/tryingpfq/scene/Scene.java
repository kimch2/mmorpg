package com.tryingpfq.scene;

import com.thread.MagicalGod;
import com.tryingpfq.domain.Player;
import com.tryingpfq.scene.message.ISceneMessage;
import org.apache.commons.lang3.time.StopWatch;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by trying on 2018/10/26.
 */
public class Scene extends MagicalGod implements Cloneable {
    /** 场景ID **/
    private int sceneId;

    /** 场景消息 **/
    private ConcurrentLinkedQueue<ISceneMessage> sceneMessages = new ConcurrentLinkedQueue<ISceneMessage>();

    /**
     * 玩家列表
     */
    private Map<Long,Player> allPlayer = new HashMap<Long, Player>();

    /** 最小睡眠时间 **/
    private static final int MIN_SLEEP_TIME = 5;

    /** 一个tick 的时间 **/
    private final static int TICK_TIME = 33;

    /** 每个Tick处理多少个场景消息 **/
    private final static int MESSAGE_PRE_TICK = 50;

    public void execute(boolean running) {
        super.updateLastExecuteTimeMillis();
        try{
            tick(super.getLastExecuteTimeMillis());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 心跳执行
     * @param utime
     */
    public void tick(long utime){
        pre();
        //处理场景消息

        //处理玩家消息


    }

    private StopWatch sw = new StopWatch();
    private void pre(){
        sw.reset();
        sw.start();
    }

    protected void processSceneMessage(){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getName() {
        return "Scene[" + sceneId + "]";
    }

    public int getProcessPeriod() {
        return TICK_TIME;
    }

    public void init() {

    }

    @Override
    public void run() {

    }

    protected int getMinSleepMillis() {
        return MIN_SLEEP_TIME;
    }
}
