package com.itfenbao.gadmins.admin.data.dto.param.accout;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateAccoutParam {
    @NotNull
    private String name;
    @NotNull
    private List<Integer> roles;
}
