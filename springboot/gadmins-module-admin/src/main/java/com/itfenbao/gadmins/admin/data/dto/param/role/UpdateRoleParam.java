package com.itfenbao.gadmins.admin.data.dto.param.role;

import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleParam {
    private String name;
    private String rcode;
    private String rdesc;
    private List<Integer> menuIds;
    private List<Integer> funcIds;
}
