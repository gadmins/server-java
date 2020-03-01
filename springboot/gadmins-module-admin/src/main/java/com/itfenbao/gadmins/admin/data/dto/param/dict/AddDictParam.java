package com.itfenbao.gadmins.admin.data.dto.param.dict;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddDictParam extends UpdateDictParam {
    private Integer pId;
}
