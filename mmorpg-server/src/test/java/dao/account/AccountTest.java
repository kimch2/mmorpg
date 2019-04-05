package dao.account;

import com.google.common.collect.Lists;
import com.tryingpfq.logic.account.entity.AccountEntity;
import com.tryingpfq.logic.account.manager.AccountManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/12/20 14:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class AccountTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountTest.class);
    @Autowired
    private AccountManager accountManager;
    @Test
    public void find(){
        if(accountManager.checkIsExit("pengfuqiang","123456")){
            LOGGER.info("{}存在","pengfuqiang");
        }
    }

    @Test
    public void update(){
        AccountEntity entity = accountManager.query("admin","admin");
        entity.setPsw("123456");
        accountManager.update(entity);
    }
    @Test
    public void add(){
        AccountEntity entity = new AccountEntity();
        entity.setAccount("admin");
        entity.setPsw("md5");
        List<Integer> ids = Lists.newArrayList();
        ids.add(200002);
        entity.setIds(ids);
        accountManager.save(entity);
    }

    @Test
    public void register(){
        accountManager.register("admin","admin");
    }
}
