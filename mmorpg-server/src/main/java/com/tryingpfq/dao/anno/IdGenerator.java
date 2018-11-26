package com.tryingpfq.dao.anno;

import java.lang.annotation.*;

/**
 * @author tryingpfq
 * @date 2018/11/26 16:47
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IdGenerator {
    String value() default "uuId";
}
