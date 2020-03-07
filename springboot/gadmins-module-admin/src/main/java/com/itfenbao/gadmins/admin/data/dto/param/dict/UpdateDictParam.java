package com.itfenbao.gadmins.admin.data.dto.param.dict;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateDictParam {
    @NotNull
    @ApiModelProperty("字典编码")
    private String dcode;
    @NotNull
    @ApiModelProperty("字典键值")
    private Integer indexValue;
    @NotNull
    @ApiModelProperty("字典标签")
    private String dvalue;
    @NotNull
    @ApiModelProperty("字典名")
    private String title;
}
