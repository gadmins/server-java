package com.itfenbao.gadmins.common.auth;


import com.itfenbao.gadmins.common.AppConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "api-access"
)
public class AuthAccessProperties {

    private AuthProperties admin = new AuthProperties(AppConfig.Access.ADMIN_TOKEN);
    private AuthProperties app = new AuthProperties(AppConfig.Access.APP_TOKEN);

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
