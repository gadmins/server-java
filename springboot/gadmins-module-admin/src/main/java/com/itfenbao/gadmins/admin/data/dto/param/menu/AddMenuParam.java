package com.itfenbao.gadmins.admin.data.dto.param.menu;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddMenuParam extends UpdateMenuParam {
    Integer id;
    @NotNull
    String type;
}
