package com.tryingpfq.common.utils;

import com.tryingpfq.common.domain.GameSession;
import com.tryingpfq.common.packet.AbstractPacket;

/**
 * @author tryingpfq
 * @date 2018/12/20 18:03
 */
public class PacketUtils {

    public static void sendPacket(GameSession session, AbstractPacket packet){
        session.getChannel().writeAndFlush(packet);
    }
}
