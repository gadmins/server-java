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
 * 系统账号角色关联表
 * </p>
 *
 * @author itfenbao
 * @since 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_rl_account_role")
@ApiModel(value="RlAccountRole对象", description="系统账号角色关联表")
public class RlAccountRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Integer accountId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;


}
