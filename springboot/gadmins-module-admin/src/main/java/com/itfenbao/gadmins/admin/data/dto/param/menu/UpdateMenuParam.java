package com.itfenbao.gadmins.admin.data.dto.param.menu;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateMenuParam {
    @NotNull
    @ApiModelProperty("菜单名")
    String txt;
    @NotNull
    @ApiModelProperty("菜单编码")
    String mcode;
    @NotNull
    Integer sortNumber;
    Integer parentId;
    @ApiModelProperty("菜单Icon")
    String icon;
    Integer funcId;
    Boolean elink;
    String url;
}