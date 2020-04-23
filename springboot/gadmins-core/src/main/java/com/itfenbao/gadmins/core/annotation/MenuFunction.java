package com.itfenbao.gadmins.core.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Function(value = "", title = "", desc = "", sort = 0)
public @interface MenuFunction {

    @AliasFor(annotation = Function.class)
    String value();

    /**
     * 功能组
     */
    @AliasFor(annotation = Function.class)
    String group() default "admin";

    /**
     * 功能名称
     */
    @AliasFor(annotation = Function.class)
    String title();

    /**
     * 功能描述
     */
    @AliasFor(annotation = Function.class)
    String desc();

    /**
     * 功能icon
     */
    @AliasFor(annotation = Function.class)
    String icon() default "";

}
