package com.tryingpfq.logic.account.service;

import com.tryingpfq.logic.account.entity.AccountEntity;
import com.tryingpfq.logic.account.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tryingpfq
 * @date 2018/11/7 16:14
 */
@Component
public class AccountService {
    @Autowired
    private AccountManager accountManager;

    public boolean login(String account,String psw){
        List<AccountEntity> entityList = accountManager.loginAccountCheck(account,psw);
        if(entityList!= null && entityList.size()>0){
            return true;
        }
        return false;
    }

    public boolean register(String account,String psw){
        return accountManager.register(account,psw);
    }
}
