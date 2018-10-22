import com.net.NetServer;
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
import org.springframework.util.StopWatch;

/**
 * Created by trying on 2018/10/20.
 */
public class GameServerStart {
    private static Logger logger = LoggerFactory.getLogger(GameServerStart.class);
    private static int port = 1111;
    static {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    }

    private static NetServer netServer = new NetServer();

    public static void main(String[] args) {
        final GameServerStart bootStrap = new GameServerStart();
        bootStrap.start();
        logger.info("GameServerStart启动服务器成功... ");
    }

    public void start(){

        netServer.start();
    }

    public void stop() {
        StopWatch sw = new StopWatch();
        sw.start();

        logger.info("StopWatch onServerClose{}", sw.getTotalTimeMillis());
        netServer.close();
    }
}
