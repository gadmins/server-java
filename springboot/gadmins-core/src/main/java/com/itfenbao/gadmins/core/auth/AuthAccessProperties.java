package com.itfenbao.gadmins.core.auth;


import com.itfenbao.gadmins.config.AppConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "api-access"
)
public class AuthAccessProperties {

    private AuthProperties admin = new AuthProperties(AppConfig.Access.ADMIN_TOKEN, AuthFrom.COOKIE);
    private AuthProperties app = new AuthProperties(AppConfig.Access.APP_TOKEN, AuthFrom.HEADER);

    public AuthAccessProperties() {
    }

    public AuthProperties getAdmin() {
        return admin;
    }

    public void setAdmin(AuthProperties admin) {
        this.admin = admin;
    }

    public AuthProperties getApp() {
        return app;
    }

    public void setApp(AuthProperties app) {
        this.app = app;
    }
}
