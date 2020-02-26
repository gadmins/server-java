package com.itfenbao.gadmins.admin.data.vo;

import lombok.Data;

import java.util.List;

@Data
public class RoleMenuVO {
    private List<String> keys;
    private List<Integer> menuIds;
    private List<Integer> funcIds;
}
