package com.tryingpfq.dao.identify;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author tryingpfq
 * @date 2018/11/28 14:50
 */
@Component
public class UUidGeneratorStrategy implements GeneratorStrategy<String> {
    @Override
    public String getGeneratorKey() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String getGeneratorType() {
        return "uuId";
    }
}
