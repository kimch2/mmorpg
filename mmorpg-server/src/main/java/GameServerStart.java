import com.tryingpfq.scene.service.SceneService;
import com.tryingpfq.server.NetServer;
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

    private static ApplicationContext context;
    static {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }
    private static NetServer netServer = new NetServer();

    public static void main(String[] args) {
        final GameServerStart bootStrap = new GameServerStart();
        bootStrap.start();
        logger.info("GameServerStart启动服务器成功... ");
    }

    public void start(){
        //加载场景
        SceneService sceneService = context.getBean(SceneService.class);
        sceneService.loadSceneDataInfo();
        netServer.start();
    }

    public void stop() {
        StopWatch sw = new StopWatch();
        sw.start();
        logger.info("StopWatch onServerClose{}", sw.getTotalTimeMillis());
        netServer.close();
    }
}
