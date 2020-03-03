package com.itfenbao.gadmins.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Menu {

    // 菜单编码
    String value();

    // 功能标题
    String title() default "";

    // 菜单描述
    String desc() default "";

    // 菜单icon
    String icon() default "home";

}
