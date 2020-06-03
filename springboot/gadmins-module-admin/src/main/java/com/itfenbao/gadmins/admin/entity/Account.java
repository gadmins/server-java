package com.itfenbao.gadmins.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-06-03
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

    @ApiModelProperty(value = "验证错误次数")
    private Integer vaildErrorTimes;

    @ApiModelProperty(value = "账户锁定")
    @TableField("`lock`")
    private Boolean lock;


}
