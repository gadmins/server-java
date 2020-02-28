package com.itfenbao.gadmins.admin.data.vo;

import com.itfenbao.gadmins.admin.entity.Account;
import com.itfenbao.gadmins.admin.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccountVO extends Account {
    private List<Role> roles;
}
