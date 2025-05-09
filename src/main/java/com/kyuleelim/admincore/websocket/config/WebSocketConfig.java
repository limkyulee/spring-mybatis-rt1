package com.kyuleelim.admincore.websocket.config;

import java.net.http.WebSocket;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @PostConstruct
    public void init() {
        log.info("WebSocketConfig init");
    }


    /**
     * @Method Name configureMessageBroker
     * @Description STOMP 메세지 라우팅 설정
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 구독(Subscribe)할 때 쓰는 경로
        registry.enableSimpleBroker("/sub");
        // 클라이언트가 보낼 때 앞에 붙이는 경로
        registry.setApplicationDestinationPrefixes("/pub");
    }

    /**
     * @Method Name registerStompEndpoints
     * @Description 클라이언트가 연결한 endpoint 지정
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 클라이언트가 처음 연결하는 주소
        registry.addEndpoint("/ws-rt1")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // 브라우저 호환성 지원
    }
}
