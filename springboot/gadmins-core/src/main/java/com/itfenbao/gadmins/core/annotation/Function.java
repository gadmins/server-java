package com.itfenbao.gadmins.core.annotation;

import java.lang.annotation.*;

/**
 * 用来标注菜单功能点
 *
 * @author itfenbao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface Function {

    // 功能编码
    String value();

    // 功能组
    String group() default "admin";

    // 功能名称
    String title();

    // 功能描述
    String desc();

    // 功能icon
    String icon() default "";

    // 排序
    int sort();

    String url() default "";

    String parentCode() default "";

    // 按钮组
    String btnGroup() default BtnGroup.OP;

    /**
     * 按钮组类型
     */
    interface BtnGroup {
        // 工具栏按钮
        String TOOLBAR = "TOOLBAR";
        // 操作栏按钮
        String OP = "OP";
    }
}
