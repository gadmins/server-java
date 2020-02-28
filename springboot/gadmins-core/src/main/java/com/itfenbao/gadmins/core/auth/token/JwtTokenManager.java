package com.itfenbao.gadmins.core.auth.token;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenManager implements TokenManager {
    @Override
    public String createToken(String uniqueId, String secret, long expire) {
        String token = Jwts.builder().setSubject(uniqueId)
                .setExpiration(new Date(System.currentTimeMillis() + expire * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compressWith(CompressionCodecs.GZIP).compact();
        return token;
    }

    @Override
    public String getUniqueIdFromToken(String token, String secret) {
        try {
            String uniqueId = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
            return uniqueId;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }
}
