package com.tryingpfq.logic.player;

import com.base.Point;
import com.net.codec.Request;
import com.net.codec.Response;
import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.timer.user.IUserCronEventHandler;
import com.tryingpfq.common.timer.user.UserCronEventService;
import com.tryingpfq.common.utils.Profile;
import com.tryingpfq.common.utils.TimeUtils;
import com.tryingpfq.logic.base.rpc.IPlayerMessage;
import com.tryingpfq.logic.player.entity.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class Player {
    private static final Logger logger = LoggerFactory.getLogger(Player.class);
    private static final short MAX_LEVEL = 150;

    private static final AtomicReferenceFieldUpdater<Player,GameSession> SessionUpdater =
            AtomicReferenceFieldUpdater.newUpdater(Player.class,GameSession.class,"gameSession");

    /**
     * 场景中每个对象有个Id
     */
    private long id;

    /**
     * 目标传送场景ID
     */
    private int prepareSceneId;
    private int prepareSceneTime;

    /**
     * 目标传送坐标点
     */
    private Point preparePoint;
    private long lastEnterSceneTime;

    /**
     * 传送前的场景ID
     */
    private int lastSceneId;

    /**
     * 传送前坐标点
     */
    private Point lastPoint;

    /**
     * 连接会话
     */
    private volatile GameSession gameSession;

    /**
     * 消息队列
     */
    protected ConcurrentLinkedQueue<Request> packetQueue = new ConcurrentLinkedQueue<Request>();
    protected ConcurrentLinkedQueue<Request> movePacketQueue = new ConcurrentLinkedQueue<Request>();

    /**
     *RPC队列
     */
    private ConcurrentLinkedQueue<IPlayerMessage> playerMessages = new ConcurrentLinkedQueue<IPlayerMessage>();

    /**
     * 该玩家是否处于可发内部消息的状态
     */
    private AtomicBoolean playerMessageEnable = new AtomicBoolean();

    /**
     * 玩家实体
     */
    private PlayerEntity playerEntity;

    /**
     * 长周期刷新时间计时器
     */
    private long nextCheckCronTime;

    /**
     * 添加消息包
     * @return
     */
    public void addMessage(Request packet){
        if(packet.isRapitPacket()){
            movePacketQueue.add(packet);
        }
        if(gameSession != null){
            final int queueSize = packetQueue.size();
            //
            //
            packetQueue.add(packet);
        }else{
            logger.error("[" + id + "] You have not AUTH yet." );
        }
    }

    /**
     * 消息队列心跳
     */
    protected void processPacket(long utime){
        try{
            for(;;){
                Request temp = movePacketQueue.poll();
                if(temp == null){
                    break;
                }
                processPacket(temp);
            }

            Request packet = packetQueue.poll();
            if(packet != null){
                processPacket(packet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理消息包
     */
    private void processPacket(Request packet){
        long statTime = TimeUtils.getCurrentMillisTime();
        try{
            packet.execue();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            Profile.profile(packet,TimeUtils.getCurrentMillisTime() - statTime);
        }
    }

    /**
     * 在玩家心跳中处理周期时间，并且保证重新登陆后会执行
     * @param utime
     */
   public void processUserCronEvent(long utime){
        try{
            if(nextCheckCronTime <= utime){
                //long lastUpdataAt = playerEntity.getLastCheckCronAt();
                UserCronEventService cronEventService = UserCronEventService.getInstance();
                List<IUserCronEventHandler> handlers = cronEventService.getHandlerAfter(utime);
                for(IUserCronEventHandler handler : handlers){
                    handler.handler(this);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //playerEntity.changeLastCheckCronAt(utime);
   }


    public boolean addPlayerMessage(IPlayerMessage message){
        if(this.playerMessageEnable.get()){
            return this.playerMessages.add(message);
        }
        return false;
    }

    public void processPlayerMessage(long utime){
        try{
            IPlayerMessage message = null;
            while((message = this.playerMessages.poll()) != null){
                message.execute(this);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clearPacket(){
        this.packetQueue.clear();
        this.movePacketQueue.clear();
    }
    public static short getMaxLevel() {
        return MAX_LEVEL;
    }

    public static AtomicReferenceFieldUpdater<Player, GameSession> getSessionUpdater() {
        return SessionUpdater;
    }

    public int getPrepareSceneId() {
        return prepareSceneId;
    }

    public void setPrepareSceneId(int prepareSceneId) {
        this.prepareSceneId = prepareSceneId;
    }

    public int getPrepareSceneTime() {
        return prepareSceneTime;
    }

    public void setPrepareSceneTime(int prepareSceneTime) {
        this.prepareSceneTime = prepareSceneTime;
    }

    public Point getPreparePoint() {
        return preparePoint;
    }

    public void setPreparePoint(Point preparePoint) {
        this.preparePoint = preparePoint;
    }

    public long getLastEnterSceneTime() {
        return lastEnterSceneTime;
    }

    public void setLastEnterSceneTime(long lastEnterSceneTime) {
        this.lastEnterSceneTime = lastEnterSceneTime;
    }

    public int getLastSceneId() {
        return lastSceneId;
    }

    public void setLastSceneId(int lastSceneId) {
        this.lastSceneId = lastSceneId;
    }

    public Point getLastPoint() {
        return lastPoint;
    }

    public void setLastPoint(Point lastPoint) {
        this.lastPoint = lastPoint;
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(GameSession gameSession) {
        this.gameSession = gameSession;
    }

    public ConcurrentLinkedQueue<Request> getPacketQueue() {
        return packetQueue;
    }

    public void setPacketQueue(ConcurrentLinkedQueue<Request> packetQueue) {
        this.packetQueue = packetQueue;
    }

    public ConcurrentLinkedQueue<Request> getMovePacketQueue() {
        return movePacketQueue;
    }

    public void setMovePacketQueue(ConcurrentLinkedQueue<Request> movePacketQueue) {
        this.movePacketQueue = movePacketQueue;
    }

    public String getName() {
        return playerEntity.getName();
    }

    public String getAccout(){
        return  playerEntity.getAccount();
    }
    public void setName(String name) {
        this.playerEntity.setName(name);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ConcurrentLinkedQueue<IPlayerMessage> getPlayerMessages() {
        return playerMessages;
    }

    public void setPlayerMessages(ConcurrentLinkedQueue<IPlayerMessage> playerMessages) {
        this.playerMessages = playerMessages;
    }

    public AtomicBoolean getPlayerMessageEnable() {
        return playerMessageEnable;
    }

    public void setPlayerMessageEnable(AtomicBoolean playerMessageEnable) {
        this.playerMessageEnable = playerMessageEnable;
    }

    /**
     * 用于服务端向客服端返回数据
     */
    public void sendPacket(AbstractPacket packet){
        try{
            if(gameSession != null){
                gameSession.sendPacket(packet);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}