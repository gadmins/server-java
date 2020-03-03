package com.itfenbao.gadmins.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用来标注功能编码
 * <pre>
 * &#064;Function("user.add")
 * public String addUser(){
 * }
 * </pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Function {

    // 功能编码
    String value();

    // 功能组
    String group() default "admin";

    // 功能名称
    String title() default "";

    // 功能描述
    String desc() default "";

    // 功能icon
    String icon() default "";

    // 排序
    int sort() default 0;

    // 父级编码
    String parentCode() default "";

    // 按钮组
    String btnGroup() default "";

    interface BtnGroup {
        String TOOLBAR = "TOOLBAR";
        String OP = "OP";
    }
}
