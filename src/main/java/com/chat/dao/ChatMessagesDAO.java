package com.chat.dao;

import com.chat.dto.chatMessages.ChatMessageCreateRequestDTO;
import com.chat.dto.chatMessages.ChatMessageReportRequestDTO;
import com.chat.dto.chatMessages.ChatMessageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatMessagesDAO {

    private final SqlSessionTemplate session;

    public int create(ChatMessageCreateRequestDTO requestDTO) {
        return session.insert("ChatMessagesMapper.create", requestDTO);
    }

    public int report(ChatMessageReportRequestDTO requestDTO) {
        return session.update("ChatMessagesMapper.report", requestDTO);
    }

    public List<ChatMessageResponseDTO> chatMessagesByRoomId(String chatRoomId) {
        return session.selectList("ChatMessagesMapper.chatMessagesByRoomId", chatRoomId);
    }
}
