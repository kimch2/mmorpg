package com.tryingpfq.logic.player.service;

import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.common.domain.enums.RoleType;
import com.tryingpfq.logic.player.entity.PlayerEntity;
import com.tryingpfq.logic.player.manager.PlayerManager;
import com.tryingpfq.logic.player.packet.CreateRoleVo;
import com.tryingpfq.logic.player.packet.ReqCreateRolePacket;
import com.tryingpfq.logic.player.packet.RespCreateRolePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tryingpfq
 * @date 2018/12/20 16:40
 */
@Component
public class PlayerService {
    private static final Logger logger = LoggerFactory.getLogger(PlayerService.class);

    @Autowired
    private PlayerManager playerManager;

    public void createRole(GameSession session, ReqCreateRolePacket packet){
        String name = packet.getRoleName();
        RoleType roleType = packet.getRoleType();
        byte sex = packet.getSex();
        logger.info("创建角色：{} sex:{} role:{}",name,roleType,sex);
        PlayerEntity entity = playerManager.findByName(name);
        RespCreateRolePacket respPacket = new RespCreateRolePacket();
        if(entity == null){
            respPacket.setResult((byte)0);
            session.sendPacket(packet);
            return;
        }
        String account = session.getAccount();
        entity = playerManager.createRole(account,name,roleType,sex);
        if(entity != null){
            CreateRoleVo roleInfo = CreateRoleVo.valueOf(entity);
            respPacket.setResult((byte) 1);
            respPacket.setPlayerId(entity.getPlayerId());
            respPacket.setRoleInfo(roleInfo);
            logger.info("{}角色创建成功",name);
            session.sendPacket(respPacket);
        }
    }
}
