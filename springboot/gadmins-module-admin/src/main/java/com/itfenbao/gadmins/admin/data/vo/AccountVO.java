package com.itfenbao.gadmins.admin.data.vo;

import com.itfenbao.gadmins.admin.entity.Account;
import com.itfenbao.gadmins.admin.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="AccountVO", description="账号VO")
public class AccountVO extends Account {
    @ApiModelProperty("角色组")
    private List<Role> roles = Collections.emptyList();
}
