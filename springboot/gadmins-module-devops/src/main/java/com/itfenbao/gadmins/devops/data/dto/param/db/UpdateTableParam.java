package com.itfenbao.gadmins.devops.data.dto.param.db;

import lombok.Data;

@Data
public class UpdateTableParam {
    String newName;
    String comment;
}
