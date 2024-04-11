package com.chat.dto.chatMessages;

import com.chat.enums.ChatReport;
import com.chat.enums.MessageType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatMessageCreateRequestDTO {
    private MessageType messageType;
    private LocalDateTime sendTime;
    private Long chatRoomId;
    private Long senderId;
    private String message;
    private ChatReport reported = ChatReport.No;
}
