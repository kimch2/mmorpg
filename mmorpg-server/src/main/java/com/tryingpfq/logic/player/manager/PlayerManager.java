package com.tryingpfq.logic.player.manager;

import com.tryingpfq.common.domain.enums.RoleType;
import com.tryingpfq.dao.provider.BaseEntityProvider;
import com.tryingpfq.logic.player.Player;
import com.tryingpfq.logic.player.entity.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/12/20 17:48
 */
@Component
public class PlayerManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerManager.class);

    @Autowired
    private BaseEntityProvider<PlayerEntity,Long> playerEntityProvider;

    public PlayerEntity findByName(String name){
        List<PlayerEntity> entityList = playerEntityProvider.query("findPlayerByName",name);
        if(entityList == null || entityList.size() == 0)
            return null;
        return entityList.get(0);
    }

    /**
     * 创建角色
     */
    public PlayerEntity createRole(String account,String name, RoleType type,int sex){
        PlayerEntity entity = new PlayerEntity(account,name,type, (byte) sex);
        try{
            playerEntityProvider.save(entity);
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error(String.format("%角色创建失败",PlayerEntity.class.getSimpleName()));
            return null;
        }
        return entity;
    }
}
