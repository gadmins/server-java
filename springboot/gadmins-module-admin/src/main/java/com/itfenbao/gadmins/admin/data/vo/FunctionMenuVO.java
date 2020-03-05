package com.itfenbao.gadmins.admin.data.vo;

import com.itfenbao.gadmins.admin.entity.Function;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author itfenbao
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FunctionMenuVO extends Function {
    private String code;
}
