package com.chat.dao;

import com.chat.dto.chatRoomMembers.ChatRoomMembersDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChatRoomMembersDAO {

    private final SqlSessionTemplate session;

    public int create(ChatRoomMembersDTO chatRoomMembersDTO) {
        return session.insert("ChatRoomMembersMapper.create", chatRoomMembersDTO);
    }

    public List<ChatRoomMembersDTO> findAllByChatRoomId(Long chatRoomId) {
        return session.selectList("ChatRoomMembersMapper.findAllByChatRoomId", chatRoomId);
    }

    public List<ChatRoomMembersDTO> findAllByUserId(String userId) {
        return session.selectList("ChatRoomMembersMapper.findAllByUserId", userId);
    }

    public int delete(ChatRoomMembersDTO chatRoomMembersDTO) {
        return session.delete("ChatRoomMembersMapper.delete", chatRoomMembersDTO);
    }
}
