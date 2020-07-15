package com.itfenbao.gadmins.admin.data.dto.param.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author itfenbao
 */
@Data
public class UpdateAccountParam {
    @NotNull
    @ApiModelProperty("角色类型")
    private List<Integer> roles;
}
