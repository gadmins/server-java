package com.itfenbao.gadmins.admin.data.dto.param.menu;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddMenuParam extends UpdateMenuParam {
    Integer id;
    @NotNull
    String type;
}
