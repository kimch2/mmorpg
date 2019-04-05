package com.tryingpfq.dao;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.io.IOException;

/**
 * @Author Tryingpfq
 * @Time 2018/11/20 22:13
 */
public class EntityProviderSessionFactoryBean extends LocalSessionFactoryBean implements InstantiationAwareBeanPostProcessor,
        PriorityOrdered {

    @Override
    public void afterPropertiesSet() throws IOException {
        super.afterPropertiesSet();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        super.setBeanFactory(beanFactory);
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
