package com.tryingpfq.logic.account.handler;

import com.annotation.WsController;
import com.annotation.WsRequest;
import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.logic.account.packet.ReqLoginPacket;
import com.tryingpfq.logic.account.packet.RespLoginPacket;
import com.tryingpfq.logic.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author tryingpfq
 * @date 2018/11/7 16:16
 */
@WsController
public class AccountHandler {
    @Autowired
    private AccountService accountService;

    @WsRequest
    public AbstractPacket login(GameSession session, ReqLoginPacket packet){
        RespLoginPacket  respPacket = new RespLoginPacket();
        if(accountService.login(packet.getAccount(),packet.getPsw())){
            respPacket.setStatus((byte)1);
        }else{
            respPacket.setStatus((byte)0);
        }
        return respPacket;
    }
}
