package com.itfenbao.gadmins.admin.data.dto.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 登录参数
 * @author itfenbao
 */
@Data
public class LoginParam {

    public final static String LOGIN_TYPE_ACCOUNT = "account";
    public final static String LOGIN_TYPE_MOBILE = "mobile";

    private String userName;
    private String password;
    private String mobile;
    private String captcha;
    @NotNull
    private String type;
}
