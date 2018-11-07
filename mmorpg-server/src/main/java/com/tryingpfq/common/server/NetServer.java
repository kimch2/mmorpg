package com.tryingpfq.common.server;

import com.tryingpfq.common.net.handler.BinaryWebSocketFrameCodec;
import com.tryingpfq.common.net.handler.GameSessionHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NetServer {
    private static Logger logger = LoggerFactory.getLogger(NetServer.class);
    private static int port = 6666;

    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup();

    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    public void start(){
        //服务类
        ServerBootstrap b = new ServerBootstrap();

        try{
            b.group(boss,worker)
                    .channel(NioServerSocketChannel.class)  //指定所使用的NIO传输Channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(buildChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
                   /* .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("IdleStateHandler",new IdleStateHandler(0,0,180));
                            pipeline.addLast("WriteTimeoutHandler",new WriteTimeoutHandler(60));
                            pipeline.addLast("reqDecoder",new RequestDecoder());
                            pipeline.addLast("serverHandler",new GameServerHandler());
                            pipeline.addLast("respEncoder",new ResponseEncoder());
                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);*/
            ChannelFuture f = b.bind(port).sync(); //异步的绑定服务器
            logger.info("NetServer Bind port {}",port);

        }catch (Exception e){
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            e.printStackTrace();
        }
    }

    public ChannelInitializer buildChannelInitializer(){
        return new ChannelInitializer<SocketChannel>(){
            @Override
            protected void initChannel(SocketChannel ch) {
                //websocket基于http协议传输，将请求和应答消息编码或解码为http消息
                ch.pipeline().addLast("http-codec",new HttpServerCodec());
                //处理大文件传输情形 块的形式
                ch.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                //将HTTP消息的多个部分组合成一条完整的HTTP消息，分段-》聚合； 参数为聚合时的最大字节
                //因为nett是分段请求的
                ch.pipeline().addLast("aggregator",new HttpObjectAggregator(65535));
                ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws"));
                ch.pipeline().addLast("gameSessionHandler",new GameSessionHandler());
                ch.pipeline().addLast("binaryWebSocketFrameCodec",new BinaryWebSocketFrameCodec());
            }
        };
    }

    public void close(){
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
