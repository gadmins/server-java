package com.itfenbao.gadmins.core.auth.role;

/**
 * 角色验证服务
 */
public interface IRoleAuthService {
    /**
     * 验证角色
     *
     * @param roles
     * @return
     */
    boolean hasRole(String... roles);
}
