package com.itfenbao.gadmins.admin.data.dto.param.db;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddTableParam extends UpdateTableParam {
    String name;
    boolean hasDelete;
}
