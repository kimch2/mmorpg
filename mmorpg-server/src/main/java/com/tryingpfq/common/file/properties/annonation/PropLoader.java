package com.tryingpfq.common.file.properties.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:39
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PropLoader {
    /**
     * properties资源路径
     */
    String value();
}
