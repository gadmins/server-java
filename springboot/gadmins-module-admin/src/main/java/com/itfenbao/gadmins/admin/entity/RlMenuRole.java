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
 * 系统角色菜单关联表
 * </p>
 *
 * @author itfenbao
 * @since 2020-03-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_rl_menu_role")
@ApiModel(value="RlMenuRole对象", description="系统角色菜单关联表")
public class RlMenuRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单ID")
    private Integer menuId;

    @ApiModelProperty(value = "角色ID")
    private Integer roleId;


}
