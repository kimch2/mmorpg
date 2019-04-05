package com.tryingpfq.logic.account.manager;

import com.tryingpfq.common.utils.SecurityUtils;
import com.tryingpfq.dao.provider.BaseEntityProvider;
import com.tryingpfq.logic.account.entity.AccountEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/12/3 17:23
 */
@Component
public class AccountManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountManager.class);

    @Autowired
    private BaseEntityProvider<AccountEntity,String> accountEntityProvide;

    public List<AccountEntity> loginAccountCheck(String account,String psw){
        List<AccountEntity> accountList = accountEntityProvide.query("findAccountByAccountAndPsw",account,psw);
        return accountList;
    }

    public boolean register(String account,String psw){
        AccountEntity entity = new AccountEntity();
        entity.setAccount(account);
        String md5Code = SecurityUtils.getMd5Code(psw);
        entity.setPsw(md5Code);
        if(checkIsExit(account,md5Code)){
            //提示失败，账号存在
        }
        accountEntityProvide.save(entity);
        return true;
    }

    /**
     * 检测账号是否存在
     * @param account
     * @param psw
     * @return
     */
    public boolean checkIsExit(String account,String psw){
        List<AccountEntity> entitys = accountEntityProvide.query("findAccountByAccountAndPsw",account,psw);
        if(entitys.size() > 0){
            return true;
        }
        return false;
    }

    public void save(AccountEntity entity){
        accountEntityProvide.save(entity);
    }

    public void update(AccountEntity entity){
        accountEntityProvide.update(entity);
    }

    public AccountEntity query(String account,String psw){
        List<AccountEntity> entities = accountEntityProvide.query("findAccountByAccountAndPsw",account,psw);
        return entities.get(0);
    }
}
