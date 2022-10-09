package com.dinusha.soft.clusterresourcemonitor.websocket;

import com.dinusha.soft.clusterresourcemonitor.constant.WebSocketConstant;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketTextHandler extends TextWebSocketHandler {

    private final Logger logger = Logger.getLogger(SocketTextHandler.class);
    @Value("${web.socket.send.message}")
    private String sendMessage;

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // The WebSocket has been closed
        logger.info("Session closed. Session ID : " + session.getId());
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // The WebSocket has been opened
        // I might save this session object so that I can send messages to it outside of this method
        // Let's send the first message
        session.sendMessage(new TextMessage("Web socket connected to the server!"));
        sendMessage(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        // A message has been received
        logger.info("Message received: " + textMessage.getPayload());
    }

    private void sendMessage(WebSocketSession session) {

        while (true) {
            try {
                Thread.sleep(Integer.parseInt(sendMessage));
            } catch (InterruptedException e) {
                logger.error(e.getStackTrace());
            }
            try {
                session.sendMessage(new TextMessage(new JSONObject(WebSocketConstant.payload).toJSONString()));
                logger.debug("WebSocket | Sending payload to frontend : " + WebSocketConstant.payload);
            } catch (Exception e) {
            }
        }
    }
}
