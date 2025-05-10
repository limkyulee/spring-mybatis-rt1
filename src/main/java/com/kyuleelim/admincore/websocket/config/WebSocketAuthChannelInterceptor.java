package com.kyuleelim.admincore.websocket.config;

import com.kyuleelim.admincore.common.auth.JwtTokenProvider;
import com.kyuleelim.admincore.common.exception.BizException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * ===========================================
 * Project      : admin-core-api
 * File Name    : WebSocketAuthChannelInterceptor.java
 * Author       : limkyulee
 * Created Date : 2025. 5. 11. 오전 1:17
 * Updated Date : 2025. 5. 11. 오전 1:17
 * Description  : WebSocket 연결 시, 인증 강제
 * ===========================================
 */
@Component
public class WebSocketAuthChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * @Method Name presend
     * @Description 메세지 전송 직전에 호출
     * @param message
     * @param channel
     * @return
     */
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        // 메세지를 STOMP 메세지로 변환하여 헤더에 접근.
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        // CONNECT 명령인 경우에만 jwt 인증 수행.
        if(StompCommand.CONNECT.equals(accessor.getCommand())) {
            // 클라이언트에서 보낸 jwt 토큰 조회.
            String token = accessor.getFirstNativeHeader("Authorization");

            // 토큰 존재 여부 및 형식 확인.
            if(token == null || !token.startsWith("Bearer ")) {
                throw new BizException("missing_token");
            }

            // 토큰 유효성 검사 실행.
            String jwt = token.substring(7);
            if(!jwtTokenProvider.validateToken(jwt)) {
                throw new BizException("invalid_token");
            }

            // 송신자 세션 정보 셋팅
            Authentication auth = jwtTokenProvider.getAuthentication(jwt);
            accessor.setUser(auth);
        }

        return message;
    }
}
