package com.chat.service;

import com.chat.dao.ChatRoomMembersDAO;
import com.chat.dto.chatRoomMembers.ChatRoomMembersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomMembersService {

    private final ChatRoomMembersDAO chatRoomMembersDAO;

    public int join(ChatRoomMembersDTO chatRoomMembersDTO) {
        return chatRoomMembersDAO.create(chatRoomMembersDTO);
    }

    public int leave(ChatRoomMembersDTO chatRoomMembersDTO) {
        return chatRoomMembersDAO.delete(chatRoomMembersDTO);
    }

    public List<ChatRoomMembersDTO> findAllByChatRoomId(Long chatRoomId) {
        return chatRoomMembersDAO.findAllByChatRoomId(chatRoomId);
    }

    public List<ChatRoomMembersDTO> findAllByUserId(String userId) {
        return chatRoomMembersDAO.findAllByUserId(userId);
    }
}
