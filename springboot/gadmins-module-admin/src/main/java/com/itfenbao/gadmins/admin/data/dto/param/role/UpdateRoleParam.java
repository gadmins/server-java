package com.itfenbao.gadmins.admin.data.dto.param.role;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class UpdateRoleParam {
    @ApiModelProperty("角色名")
    private String name;
    @ApiModelProperty("角色编码")
    private String rcode;
    @ApiModelProperty("菜单描述")
    private String rdesc;
    private List<Integer> menuIds;
    private List<Integer> funcIds;
}
