package com.chat.service;

import com.chat.dao.ChatMessagesDAO;
import com.chat.dto.chatMessages.ChatMessageCreateRequestDTO;
import com.chat.dto.chatMessages.ChatMessageReportRequestDTO;
import com.chat.dto.chatMessages.ChatMessageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessagesService {

    private final ChatMessagesDAO chatMessagesDAO;

    public int create(ChatMessageCreateRequestDTO requestDTO) {
        return chatMessagesDAO.create(requestDTO);
    }

    public int report(ChatMessageReportRequestDTO requestDTO) {
        return chatMessagesDAO.report(requestDTO);
    }

    public List<ChatMessageResponseDTO> chatMessagesByRoomId(String chatRoomId) {
        return chatMessagesDAO.chatMessagesByRoomId(chatRoomId);
    }
}
