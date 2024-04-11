package com.chat.controller;

import com.chat.dto.chatMessages.ChatMessageCreateRequestDTO;
import com.chat.service.ChatMessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatMessageController {

    @Autowired
    private ChatMessagesService chatMessagesService;

    @MessageMapping("/chat/send")
    @SendTo("/topic/messages")
    public ChatMessageCreateRequestDTO sendMessage(@Payload ChatMessageCreateRequestDTO message) {
        chatMessagesService.create(message);  // 메시지 저장
        return message;
    }
}