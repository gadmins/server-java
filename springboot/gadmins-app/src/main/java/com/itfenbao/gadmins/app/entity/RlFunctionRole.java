package com.itfenbao.gadmins.app.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色功能关联表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_rl_function_role")
public class RlFunctionRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 功能ID
     */
    private Integer funId;

    /**
     * 角色ID
     */
    private Integer roleId;


}
