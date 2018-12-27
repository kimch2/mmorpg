package com.tryingpfq.common.file.properties.annonation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tryingpfq
 * @date 2018/12/27 11:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PropKey {
    /**
     * 字段key
     */
    String value();

    /**
     * 默认值 在没有配置 或者配置为空的时候使用
     */
    String defaultVal() default "";
}
