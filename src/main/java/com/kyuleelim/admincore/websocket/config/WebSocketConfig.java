package com.kyuleelim.admincore.websocket.config;

import java.net.http.WebSocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author limkyulee
 * @version 1.0, 5/5/25
 * @see WebSocket STOMP Config
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /**
     * @Method Name configureMessageBroker
     * @Description STOMP 메세지 라우팅 설정
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 구독(Subscribe)할 때 쓰는 경로
        registry.enableSimpleBroker("/topic");
        // 클라이언트가 보낼 때 앞에 붙이는 경로
        registry.setApplicationDestinationPrefixes("/app");
    }

    /**
     * @Method Name registerStompEndpoints
     * @Description 클라이언트가 연결한 endpoint 지정
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 처음 연결하는 주소
        registry.addEndpoint("/rt1-websocket")
//                .setAllowedOrigins("")
                .withSockJS(); // 브라우저 호환성 지원
    }
}
