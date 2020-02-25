package com.itfenbao.gadmins.admin.data.dto.param.accout;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddAccoutParam extends UpdateAccoutParam {
    @NotNull
    private String password;
}
