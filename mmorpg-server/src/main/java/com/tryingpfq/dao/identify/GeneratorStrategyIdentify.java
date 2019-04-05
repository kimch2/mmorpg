package com.tryingpfq.dao.identify;

import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author tryingpfq
 * @date 2018/11/28 14:52
 */
@Component
public class GeneratorStrategyIdentify implements ApplicationContextAware {
    private ApplicationContext context;

    private Map<String,GeneratorStrategy> key2GenerStrategy = Maps.newHashMap();

    private static GeneratorStrategyIdentify identify;

    public static GeneratorStrategyIdentify getInstance() { return identify ; }
    @PostConstruct
    public void init(){
        Map<String,GeneratorStrategy> beans2Type = context.getBeansOfType(GeneratorStrategy.class);
        for(GeneratorStrategy gs : beans2Type.values()){
            key2GenerStrategy.put(gs.getGeneratorType(),gs);
        }
        identify = this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public GeneratorStrategy getGeneratorStrategy(String type){
        return key2GenerStrategy.get(type);
    }
}
