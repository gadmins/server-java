package com.itfenbao.gadmins.admin.exception;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 2:57 下午
 */
public class LoginFailException extends RuntimeException {
    public LoginFailException(String message) {
        super(message);
    }
}
