package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_menu")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String mCode;

    /**
     * 菜单文本
     */
    private String txt;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单类型
     */
    private String type;

    /**
     * 排序
     */
    private Integer sortNumber;

    /**
     * 功能ID
     */
    private Integer funId;

    /**
     * 父级ID
     */
    private Integer pId;


}
