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
 * 系统账号表
 * </p>
 *
 * @author itfenbao
 * @since 2020-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_admin_account")
@ApiModel(value="Account对象", description="系统账号表")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;


}
