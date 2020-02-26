package com.itfenbao.gadmins.admin.data.vo;

import com.itfenbao.gadmins.admin.entity.Accout;
import com.itfenbao.gadmins.admin.entity.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AccoutVO extends Accout {
    private List<Role> roles;
}
