package com.dinusha.soft.clusterresourcemonitor.websocket;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final Logger logger = Logger.getLogger(WebSocketConfig.class);

    @Bean
    public WebSocketHandler myMessageHandler() {
        return new SocketTextHandler();
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        logger.debug("Registering Web Socket endpoint : " + "/v1/web-socket-endpoint");
        registry.addHandler(myMessageHandler(), "/v1/web-socket-endpoint");
    }

}