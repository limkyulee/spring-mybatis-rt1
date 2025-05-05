package com.kyuleelim.admincore.websocket.controller;

import com.kyuleelim.admincore.websocket.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author limkyulee
 * @version 1.0, 5/5/25
 * @see Simple Chat Message Controller
 */
@Controller
public class MessageController {

    /**
     * @Method Name sendMessage
     * @Description /topic/listen 구독 시, 전송
     * @param message
     * @return
     */
    @MessageMapping("/chat.send")
    @SendTo("/topic/listen")
    public Message sendMessage(Message message){
        return message;
    }
}
