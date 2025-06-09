package com.kyuleelim.admincore.websocket.controller;

import com.kyuleelim.admincore.websocket.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * @author limkyulee
 * @version 1.0, 5/5/25
 * @see Simple Chat Message Controller
 */
@Slf4j
@Controller
public class MessageController {

    /**
     * @Method Name sendMessage
     * @Description /topic/listen 구독 시, 전송
     * @param message
     * @return
     */
    @MessageMapping("/chat")
    @SendTo("/sub/messages")
    public Message sendMessage(Message message, Principal principal){
        // 송신자 셋팅
        message.setSender(principal.getName());
        return message;
    }
}
