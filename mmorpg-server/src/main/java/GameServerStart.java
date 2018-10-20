import com.net.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import com.net.codec.RequestDecoder;
import com.net.codec.ResponseEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by trying on 2018/10/20.
 */
public class GameServerStart {
    private static Logger logger = LoggerFactory.getLogger(GameServerStart.class);
    private static int port = 1111;
    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }
    public static void main(String[] args) {

        start();
        logger.info("GameServerStart启动服务器成功... ");
    }

    public static void start(){
        //服务类
        ServerBootstrap b = new ServerBootstrap();

        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try{
            b.group(boos,worker)
                    .channel(NioServerSocketChannel.class)  //指定所使用的NIO传输Channel
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast("reqDecoder",new RequestDecoder());
                            pipeline.addLast("respEncoder",new ResponseEncoder());
                            pipeline.addLast("serverHandler",new ServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                        .childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture f = b.bind(port).sync(); //异步的绑定服务器
            f.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
