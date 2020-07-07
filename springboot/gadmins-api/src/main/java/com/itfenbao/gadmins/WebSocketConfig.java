package com.itfenbao.gadmins;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author itfenbao
 * @version 1.0
 * @description: WebSocket配置
 * @date :2020/7/6 5:15 下午
 */
@Configuration
public class WebSocketConfig {

    @Bean
    @Profile({"dev", "test"})
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}

