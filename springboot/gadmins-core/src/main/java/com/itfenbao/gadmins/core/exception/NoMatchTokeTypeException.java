package com.itfenbao.gadmins.core.exception;

/**
 * TODO
 *
 * @author itfenbao
 * @version 1.0
 * @date 2020/4/6 5:52 下午
 */
public class NoMatchTokeTypeException extends RuntimeException {
    public NoMatchTokeTypeException() {
        super("Token匹配失败");
    }
}
