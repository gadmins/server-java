package com.itfenbao.gadmins.admin.data.dto.param.account;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateAccountParam {
    @NotNull
    private String name;
    @NotNull
    private List<Integer> roles;
}
