package com.annotation;

import java.lang.annotation.*;

/**
 * @author tryingpfq
 * @date 2018/11/1 16:09
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface WsController {
}
