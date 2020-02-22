package com.itfenbao.gadmins.admin.data.dto.param.menu;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateMenuParam {
    @NotNull
    String txt;
    @NotNull
    String mcode;
    @NotNull
    Integer sortNumber;
    Integer parentId;
    String icon;
}