package com.itfenbao.gadmins.admin.data.dto.param.account;

import lombok.Data;

/**
 * TODO
 *
 * @author itfenbao
 * @version 1.0
 * @date 2020/4/12 10:10 下午
 */
@Data
public class ModifyPwdParam {
    private String oldPwd;
    private String newPwd;
}
