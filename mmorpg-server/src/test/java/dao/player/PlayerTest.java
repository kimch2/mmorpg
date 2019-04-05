package dao.player;

import com.tryingpfq.common.domain.enums.RoleType;
import com.tryingpfq.logic.player.entity.PlayerEntity;
import com.tryingpfq.logic.player.manager.PlayerManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tryingpfq
 * @date 2018/12/21 10:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PlayerTest {
    @Autowired
    private PlayerManager playerManager;

    @Test
    public void addPlayer(){
        PlayerEntity entity = playerManager.createRole("pengfuqiang","彭富强", RoleType.ZHAN_SHI,1);
    }

    @Test
    public void find(){
        PlayerEntity entity = playerManager.findByName("彭富强");
    }
}
