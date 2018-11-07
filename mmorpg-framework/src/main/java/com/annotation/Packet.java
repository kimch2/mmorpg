package com.annotation;

import java.lang.annotation.*;
/**
 * @author tryingpfq
 * @date 2018/11/1
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Packet {
    short value();
    String description() default "";
}
