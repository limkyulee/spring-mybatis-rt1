package com.kyuleelim.admincore.websocket.dto;

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
    private String type; // CHAT, JOIN, LEAVE
}
