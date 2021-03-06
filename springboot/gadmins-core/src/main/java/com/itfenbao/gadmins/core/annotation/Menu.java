package com.itfenbao.gadmins.core.annotation;

import java.lang.annotation.*;

/**
 * 菜单注解
 * @author itfenbao
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Menu {

    // 菜单编码
    String value();

    // 父级菜单编码
    String parentCode();

    // 功能标题
    String title() default "";

    // 菜单描述
    String desc() default "";

    // 菜单icon
    String icon() default "home";

    // 菜单url
    String url();

    // 排序
    int sort() default 0;

}
