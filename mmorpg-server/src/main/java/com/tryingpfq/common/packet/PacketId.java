package com.tryingpfq.common.packet;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tryingpfq
 * @date 2018/11/1
 */
@Component
public class PacketId {

    private static BiMap<Short,AbstractPacket> packetId2AbstractPacket = HashBiMap.create();
    private static Map<Short,Codec> packetId2Codec = new HashMap<Short, Codec>();

    /** 登陆10001开始 **/

    /**请求登陆**/
    public static final short REQ_LOGIN = 10001;
    /**响应登陆**/
    public static final short RESP_LOGIN = 10002;
    /***注册账号 **/
    public static final short REQ_REGISTER = 10003;
    /** 返回注册结果 **/
    public static final short RESP_REGISTER = 10004;
    /** 创建角色信息 **/
    public static final short REQ_CREATE_ROLE = 10005;
    /** 返回创建角色信息 **/
    public static final short RESP_CREATE_ROLE = 10006;


    public void registerPacketId2AbstractPacket(AbstractPacket packet){
        packetId2AbstractPacket.put(packet.getPacketId(),packet);
    }
    public void registerPacketId2Codec(AbstractPacket packet){
        packetId2Codec.put(packet.getPacketId(),ProtobufProxy.create(packet.getClass()));
    }

    public static AbstractPacket getAbstractPacket(short packetId){
        return packetId2AbstractPacket.get(packetId);
    }

    public static Codec getCodec(short packetId){
        return packetId2Codec.get(packetId);
    }

}
