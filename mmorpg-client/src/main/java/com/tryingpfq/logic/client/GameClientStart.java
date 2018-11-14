package com.tryingpfq.logic.client;

import com.annotation.Packet;
import com.tryingpfq.common.packet.AbstractPacket;
import com.tryingpfq.common.packet.PacketId;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author tryingpfq
 * @date 2018/11/8 9:41
 */
@Component
public class GameClientStart {
    private static final Logger logger = LoggerFactory.getLogger(GameClientStart.class);

    @Autowired
    private PacketId packetId;

    public Reflections reflections = new Reflections("com.tryingpfq.logic");

    private static NetClient netClient = new NetClient();

    public void start(){
        initAllPacket();
        netClient.start();
    }
    // TODO 将所有包进行注册
    private void initAllPacket(){
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Packet.class);
        logger.info("开始注册{}个packet",classes.size());
        for(Class clazz : classes){
            try{
                AbstractPacket packet = (AbstractPacket)clazz.newInstance();
                packetId.registerPacketId2Codec(packet);
                packetId.registerPacketId2AbstractPacket(packet);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
