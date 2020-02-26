package com.itfenbao.gadmins.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.itfenbao.gadmins.core.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色功能关联表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_rl_function_role")
@ApiModel(value="RlFunctionRole对象", description="系统角色功能关联表")
public class RlFunctionRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "功能ID")
    private Integer funcId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;


}
