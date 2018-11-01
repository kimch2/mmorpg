package com.annotation;

import java.lang.annotation.*;

/**
 * @author tryingpfq
 * @date 2018/11/1 16:12
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WsRequest {
}
