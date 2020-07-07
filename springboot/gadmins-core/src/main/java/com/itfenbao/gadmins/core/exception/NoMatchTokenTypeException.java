package com.itfenbao.gadmins.core.exception;

/**
 * Token匹配失败
 *
 * @author itfenbao
 * @version 1.0
 * @date 2020/4/6 5:52 下午
 */
public class NoMatchTokenTypeException extends RuntimeException {
    public NoMatchTokenTypeException() {
        super("Token匹配失败");
    }
}
