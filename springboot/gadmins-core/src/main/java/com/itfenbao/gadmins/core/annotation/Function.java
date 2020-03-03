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
    int sort();

    // 是否是菜单功能
    boolean menu() default false;

    String url() default "";

    String parentCode() default "";

    // 按钮组
    String btnGroup() default BtnGroup.OP;

    // 按钮组类型
    interface BtnGroup {
        // 工具栏按钮
        String TOOLBAR = "TOOLBAR";
        // 操作栏按钮
        String OP = "OP";
    }
}
