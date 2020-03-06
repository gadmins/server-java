package com.itfenbao.gadmins.core.web.service;

/**
 * 检测用户是否有权限
 *
 * @author itfenbao
 */
public interface IUserAuthService {

    /**
     * 是有有权限
     *
     * @param authCode 权限编码
     * @return
     */
    boolean hasAuth(String authCode);

}
