package com.tryingpfq.logic.client;

import com.net.codec.RequestDecoder;
import com.net.codec.ResponseDecoder;
import com.tryingpfq.common.handler.ClientHandler;
import com.tryingpfq.logic.account.packet.ReqLoginPacket;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tryingpfq
 * @date 2018/11/8 9:45
 */
public class NetClient {
    private static final Logger logger = LoggerFactory.getLogger(NetClient.class);

    private static Channel channel;

    public  void start(){
        ServerBootstrap bootstrapToClient = new ServerBootstrap();

        EventLoopGroup boss=null;
        EventLoopGroup worker=null;

        try{

            boss=new NioEventLoopGroup();
            worker=new NioEventLoopGroup();

            bootstrapToClient.group(boss, worker).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("http-codec", new HttpServerCodec());
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                            ch.pipeline().addLast("protocolHandler",new WebSocketServerProtocolHandler("/ws"));
                            ch.pipeline().addLast("decoder",new ResponseDecoder());
                            ch.pipeline().addLast("encoder",new RequestDecoder());
                            ch.pipeline().addLast("clientHandler",new ClientHandler());
                        }
                    });

            ChannelFuture f = bootstrapToClient.bind(6666).sync();
            channel = f.channel();
            logger.info("客服端启动成功...");
            ReqLoginPacket packet = new ReqLoginPacket();
            packet.setAccount("admin");
            packet.setAccount("123456");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
