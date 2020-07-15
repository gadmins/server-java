package com.itfenbao.gadmins.admin.data.dto.param.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;


/**
 * @author itfenbao
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AddAccountParam extends UpdateAccountParam {
    @NotNull
    @ApiModelProperty("账号")
    private String name;
    @NotNull
    @ApiModelProperty("密码")
    private String password;
}
