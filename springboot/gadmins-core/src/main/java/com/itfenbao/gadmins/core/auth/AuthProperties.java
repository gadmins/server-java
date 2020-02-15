package com.itfenbao.gadmins.core.auth;

public class AuthProperties {
    private boolean open = true;
    private String key;
    private String secret;

    public AuthProperties() {
    }

    public AuthProperties(String key) {
        this();
        this.key = key;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
