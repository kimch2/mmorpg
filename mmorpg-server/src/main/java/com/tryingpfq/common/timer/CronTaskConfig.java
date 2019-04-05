package com.tryingpfq.common.timer;

import com.tryingpfq.common.file.properties.ProConfig;
import com.tryingpfq.common.file.properties.ProConfigListener;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:10
 */
public class CronTaskConfig {
    public static final ProConfig CRON_TASK_CONFIG = null;  //从配置中读取

    //get cron
    public static String getCron(CronTaskType taskType){
        return "";
    }
}
