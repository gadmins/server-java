package com.itfenbao.gadmins.core.auth;

import com.itfenbao.gadmins.core.auth.token.JwtTokenManager;
import com.itfenbao.gadmins.core.auth.token.TokenManager;

public class AuthProperties {
    private boolean open = true;
    private Class<? extends TokenManager> tokenImplement = JwtTokenManager.class;

    /**
     * Cookies/Header 的key值
     */
    private String key;

    /**
     * key 在哪里
     */
    private AuthFrom keyFrom;
    /**
     * 秘钥
     */
    private String secret;
    /**
     * 有效期：单位秒,默认1天
     */
    private long expireTime = 1 * 24 * 60 * 60;

    public AuthProperties() {
    }

    public AuthProperties(String key, AuthFrom keyFrom) {
        this.key = key;
        this.keyFrom = keyFrom;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Class getTokenImplement() {
        return tokenImplement;
    }

    public void setTokenImplement(Class tokenImplement) {
        this.tokenImplement = tokenImplement;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public AuthFrom getKeyFrom() {
        return keyFrom;
    }

    public void setKeyFrom(AuthFrom keyFrom) {
        this.keyFrom = keyFrom;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

}
