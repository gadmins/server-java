package com.itfenbao.gadmins.app;

import com.itfenbao.gadmins.core.auth.token.TokenManager;
import org.springframework.stereotype.Component;

@Component
public class RedisTokenManager implements TokenManager {
    @Override
    public String createToken(String uniqueId, String secret, long expire) {
        return null;
    }

    @Override
    public String getUniqueIdFromToken(String token, String secret) {
        return null;
    }

    @Override
    public void removeToken(String token) {

    }

    @Override
    public void refreshToken(String token, long expire) {

    }
}
