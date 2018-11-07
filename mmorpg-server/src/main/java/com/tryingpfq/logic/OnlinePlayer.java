package com.tryingpfq.logic;

import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.common.domain.enums.CloseCause;
import com.tryingpfq.logic.player.Player;
import org.apache.commons.lang3.concurrent.ConcurrentUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class OnlinePlayer {
    private static final Logger log = LoggerFactory.getLogger(OnlinePlayer.class);

    private static final OnlinePlayer instance = new OnlinePlayer();

    /**
     * 玩家Id -> 会话
     */
    private final ConcurrentMap<Long,GameSession> PLAYERID_TO_SESSION = new ConcurrentHashMap<Long, GameSession>();

    /**
     * 玩家账号 -> 玩家ID
     */
    private final ConcurrentMap<String,List<Long>> ACCOUNT_TO_PLAYERID = new ConcurrentHashMap<String, List<Long>>();

    /**
     * 玩家昵称 -> 玩家ID
     */
    private final ConcurrentMap<String,Long> NAME_TO_PLAYERID = new ConcurrentHashMap<String, Long>();

    /**
     * key -> 玩家Id
     */
    private final ConcurrentMap<String,Long> KEY_TO_PLAYERID = new ConcurrentHashMap<String, Long>();

    private OnlinePlayer(){}

    public final static OnlinePlayer getInstance(){
        return instance;
    }

    public boolean registerSession(Player player, GameSession session){
        String key = session.getAsKey();
        Long oldId = KEY_TO_PLAYERID.putIfAbsent(key,player.getId());
        if(oldId != null){
            GameSession oldSession = PLAYERID_TO_SESSION.get(oldId);
            if(oldSession != null){
                try{

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    oldSession.close(CloseCause.Duplicate_Login,session.getIp());
                }
            }
            return false;
        }

        session.setRegistered();
        session.setPlayer(player);
        PLAYERID_TO_SESSION.put(player.getId(),session);
        NAME_TO_PLAYERID.put(player.getName(),player.getId());

        addPlayerIdByAccount(player.getAccout(),player.getId());

        log.debug("{}[{}] register",session.getAccount(),player.getId());
        return true;
    }

    public boolean addPlayerIdByAccount(String account,Long playerId){
        List<Long> ids = getPlayerIdsByAccount(account);
        synchronized (ids){
            return ids.add(playerId);
        }
    }

    public List<Long> getPlayerIdsByAccount(String account){
        List<Long> ids = ACCOUNT_TO_PLAYERID.get(account);
        if(ids == null){
            ids = ConcurrentUtils.putIfAbsent(ACCOUNT_TO_PLAYERID,account,new LinkedList<Long>());
        }
        return ids;
    }
}
