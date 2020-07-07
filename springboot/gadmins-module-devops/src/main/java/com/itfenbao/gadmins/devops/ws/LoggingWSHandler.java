package com.itfenbao.gadmins.devops.ws;

import com.itfenbao.gadmins.core.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author itfenbao
 * @version 1.0
 * @description:
 * @date :2020/7/7 5:06 下午
 */
@Component
public class LoggingWSHandler extends TextWebSocketHandler {

    @Autowired
    RxLog rxLog;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String token = TokenUtils.getToken(session.getHandshakeHeaders());
        WsSessionManager.add(token, session);
        rxLog.start();
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        String token = TokenUtils.getToken(session.getHandshakeHeaders());
        WsSessionManager.remove(token);
        if (session.isOpen()) {
            session.close();
        }
        if (WsSessionManager.SESSION_POOL.isEmpty()) {
            rxLog.stop();
        }
    }
}
