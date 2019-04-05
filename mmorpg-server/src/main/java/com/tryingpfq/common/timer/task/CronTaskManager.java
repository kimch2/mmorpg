package com.tryingpfq.common.timer.task;

import com.google.common.collect.Lists;
import com.tryingpfq.common.timer.CronTaskType;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author tryingpfq
 * @date 2019/3/12 16:52
 */
@Component
public class CronTaskManager implements BeanPostProcessor {
    private List<AbstractCronTriggerTask> taskList = Lists.newArrayList();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = AopUtils.getTargetClass(bean);
        //根据CronTrigger注解获取所有方法
        List<Method> methodList = Lists.newArrayList();     //考虑这里怎么去过滤呢
        CronTriggerTask cronTriggerTask = null;
        for(Method method :methodList){
          //  cronTriggerTask = (CronTriggerTask) method.getAnnotations(CronTriggerTask.class);
            taskList.add(new AbstractCronTriggerTask(CronTaskType.ZERO_TIME) {

                @Override
                protected void handle(long time) {

                }

            });
        }
        return bean;
    }
}
