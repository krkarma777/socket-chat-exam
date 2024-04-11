package com.chat.dto.chatMessages;

import com.chat.enums.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageResponseDTO {

    private Long id;
    private MessageType messageType;
    private LocalDateTime sendTime;
    private Long chatRoomId;
    private Long senderId;
    private String message;
}
