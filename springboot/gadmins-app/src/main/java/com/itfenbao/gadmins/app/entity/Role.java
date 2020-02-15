package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_role")
public class Role extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    private String rCode;

    /**
     * 角色名
     */
    private String name;

    /**
     * 描述
     */
    private String rDesc;

    /**
     * 是否超管
     */
    private Boolean superAdmin;


}
