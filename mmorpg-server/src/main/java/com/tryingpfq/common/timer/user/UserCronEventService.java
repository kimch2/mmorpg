package com.tryingpfq.common.timer.user;

import com.google.common.collect.Lists;
import com.tryingpfq.common.timer.CronEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author tryingpfq
 * @date 2019/1/17 15:14
 */
@Component
public class UserCronEventService implements ApplicationContextAware,InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserCronEventService.class);

    private ApplicationContext applicationContext;

    private static UserCronEventService instance;

    /** 启动的时候初始化定时列表 **/
    private List<CronEntry> cronList = Lists.newArrayList();

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,IUserCronEventHandler> beans = applicationContext.getBeansOfType(IUserCronEventHandler.class);
        if(beans != null){
            try{
                Iterator<IUserCronEventHandler> iterator = (Iterator<IUserCronEventHandler>) beans.values();
                while (iterator.hasNext()){
                    IUserCronEventHandler eventHandler = iterator.next();
                    String cron = eventHandler.getCron();
                    CronEntry entry = new CronEntry(cron,eventHandler);
                    this.cronList.add(entry);
                }
            }catch (Exception e){
                LOGGER.warn("the handler can not match");
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.instance = this;
        this.applicationContext = applicationContext;
    }

    public List<IUserCronEventHandler> getHandlerAfter(long utime){
        List<IUserCronEventHandler> handlers = Lists.newArrayList();
        for(CronEntry entry : cronList){
            if(entry.hasMoreRecentMatch(utime)){
                handlers.add(entry.getHandler());
            }
        }
        return handlers;
    }

    public static UserCronEventService getInstance(){
        return instance;
    }
}
