package com.itfenbao.gadmins.core.auth.token;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 11:43 上午
 */
public interface IPassTokenService {
    /**
     * 是否通过token
     *
     * @param uri
     * @param method
     * @return
     */
    boolean passToken(String uri, String method);
}
