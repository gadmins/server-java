package com.itfenbao.gadmins.devops.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/6 5:21 下午
 */
@Slf4j
@Component
@ServerEndpoint("/ws/logging")
public class LoggingWSServer implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    private RxLog rxLog() {
        return context.getBean(RxLog.class);
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        rxLog().addWebSocket(this);
        rxLog().start();
    }

    @OnClose
    public void onClose() {
        rxLog().removeWebSocket(this);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("onMessage(" + message + ")");
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    @PreDestroy
    public void destory() throws Exception {
        if (this.session != null) {
            this.session.close();
            this.session = null;
        }
        rxLog().removeWebSocket(this);
        rxLog().stop();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

}