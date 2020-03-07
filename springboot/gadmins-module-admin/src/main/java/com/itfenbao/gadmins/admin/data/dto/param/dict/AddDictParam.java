package com.itfenbao.gadmins.admin.data.dto.param.dict;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddDictParam extends UpdateDictParam {
    @ApiModelProperty("父ID")
    private Integer pId;
}
