package com.tryingpfq.logic.client;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.google.common.collect.Lists;
import com.tryingpfq.common.handler.ClientHandler;
import com.tryingpfq.common.net.handler.BasePacketCodec;
import com.tryingpfq.common.packet.PacketId;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/11/8 9:45
 */
public class NetClient {
    private static final Logger logger = LoggerFactory.getLogger(NetClient.class);

    public void start(){
        Bootstrap bootstrap = new Bootstrap();
        try{
            EventLoopGroup group = new NioEventLoopGroup();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress("127.0.0.1",6666)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            ch.pipeline().addLast("BasePacketCodec",new BasePacketCodec());
                            ch.pipeline().addLast("clientHandler",new ClientHandler());
                        }
                    });
            ChannelFuture f = bootstrap.connect().sync();
            final Channel channel = f.channel();
            logger.info("客服端启动成功...");
            new Thread(() -> clientSendPacket(channel)).start();
           /* new Thread(){
                @Override
                public void run() {
                    clientSendPacket(channel);
                }
            }.start();*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void clientSendPacket(Channel channel) {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            while(true){
                try{
                    System.out.println("输入协议id:");
                    String s = bufferedReader.readLine();
                    short packetId = Short.parseShort(s);
                    System.out.println("协议数据-------------------");
                    List<String> strings = Lists.newArrayList();
                    Codec codec = PacketId.getCodec(packetId);
                    Class clazz = getProtocalClass(codec);
                    Field[] declareFileds = clazz.getDeclaredFields();
                    for(Field field : declareFileds){
                        System.out.println(field.getName()+":");
                        s = bufferedReader.readLine();
                        strings.add(s);
                    }
                    Object msg = ceatePacket(packetId,codec,strings);
                    channel.writeAndFlush(msg);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Class getProtocalClass(Codec codec) {
        Type type = codec.getClass().getGenericInterfaces()[0];
        Type[] params = ((ParameterizedType) type).getActualTypeArguments();
        Type param = params[0];
        return (Class) param;
    }

    private Object ceatePacket(short packetId,Codec codec,List<String> strs){
        Class clazz = getProtocalClass(codec);
        Object o = null;
        try {
            o = clazz.newInstance();
            Field[] declaredFileds = clazz.getDeclaredFields();
            int i = 0;
            for(Field field : declaredFileds){
                field.setAccessible(true);
                if(field.getType().equals(String.class)){
                    field.set(o,strs.get(i++));
                }else{

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return o;
    }
}
