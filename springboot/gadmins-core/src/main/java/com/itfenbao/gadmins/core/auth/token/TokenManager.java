package com.itfenbao.gadmins.core.auth.token;

public interface TokenManager {
    /**
     * 创建token
     *
     * @param uniqueId
     * @param secret
     * @param expire
     * @return
     */
    String createToken(String uniqueId, String secret, long expire);

    /**
     * 获取uniqueId
     *
     * @param token
     * @param secret
     * @return
     */
    String getUniqueIdFromToken(String token, String secret);

    /**
     * 移除token
     *
     * @param token
     */
    void removeToken(String token);
}
