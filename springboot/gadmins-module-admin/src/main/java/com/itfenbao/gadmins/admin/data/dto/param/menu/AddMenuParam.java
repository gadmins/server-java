package com.itfenbao.gadmins.admin.data.dto.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddMenuParam extends UpdateMenuParam {
    @NotNull
    @ApiModelProperty("菜单类型")
    String type;
}
