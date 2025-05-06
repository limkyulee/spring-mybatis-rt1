package com.kyuleelim.admincore.websocket.dto;

import com.kyuleelim.admincore.websocket.domain.MessageType;
import lombok.Data;

/**
 * @author limkyulee
 * @version 1.0, 5/5/25
 * @see Chat Message DTO
 */
@Data
public class Message {
    private String sender;
    private String content;
    private MessageType type; // CHAT, JOIN, LEAVE
}
