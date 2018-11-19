package com.tryingpfq.logic.account.service;

import org.springframework.stereotype.Component;

/**
 * @author tryingpfq
 * @date 2018/11/7 16:14
 */
@Component
public class AccountService {
    public boolean login(String account,String psw){
        System.out.println(account + ":" + psw);
        return true;
    }

    public boolean register(String account,String psw){
        System.out.println(account + ":" + psw);
        return true;
    }
}
