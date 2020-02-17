package com.itfenbao.gadmins.app.data.vo;

import com.itfenbao.gadmins.app.entity.Accout;
import lombok.Data;

@Data
public class AccoutVO extends Accout {
    private long roleId = -1;
}
