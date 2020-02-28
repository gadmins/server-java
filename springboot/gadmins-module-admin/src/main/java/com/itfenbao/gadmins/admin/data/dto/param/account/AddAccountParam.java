package com.itfenbao.gadmins.admin.data.dto.param.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
public class AddAccountParam extends UpdateAccountParam {
    @NotNull
    private String password;
}
