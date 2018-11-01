package com.tryingpfq.annotation;

import java.lang.annotation.*;

/**
 * @author tryingpfq
 * @date 2018/11/1 15:47
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Description {
    String value();
}
