package com.net;

import com.net.codec.RequestDecoder;
import com.net.codec.ResponseEncoder;
import com.net.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NetServer {
    private static Logger logger = LoggerFactory.getLogger(NetServer.class);
    private static int port = 1111;

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
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("IdleStateHandler",new IdleStateHandler(0,0,180));
                            pipeline.addLast("WriteTimeoutHandler",new WriteTimeoutHandler(60));
                            pipeline.addLast("reqDecoder",new RequestDecoder());
                            pipeline.addLast("respEncoder",new ResponseEncoder());
                            pipeline.addLast("serverHandler",new ServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture f = b.bind(port).sync(); //异步的绑定服务器
            logger.info("NetServer Bind port{}",port);

        }catch (Exception e){
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            e.printStackTrace();
        }
    }

    public void close(){
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
