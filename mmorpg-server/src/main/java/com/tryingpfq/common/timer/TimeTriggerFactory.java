package com.tryingpfq.common.timer;

import com.google.common.collect.Lists;
import com.tryingpfq.common.utils.StringUtils;

import java.text.ParseException;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:34
 *
 * TimeTrigger 工厂
 */
public class TimeTriggerFactory {

    /**
     *  cron格式
     */
    public static TimeTrigger parseCron(String cronExp) throws ParseException {
        if(StringUtils.isBlank(cronExp)){
            throw new NullPointerException();
        }
        String[] crons = StringUtils.toStringArray(cronExp,";");
        List<TimeTrigger> timeTriggerList = Lists.newArrayList();
        for(String cron : crons){
            timeTriggerList.add(new CronTimeTrigger(cronExp));
        }
        return timeTriggerList.size() > 1 ? new CombineTimeTrigger(timeTriggerList) : timeTriggerList.get(0);
    }
}
