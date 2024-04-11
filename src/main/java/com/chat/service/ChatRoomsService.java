package com.chat.service;

import com.chat.dao.ChatRoomsDAO;
import com.chat.dto.chatRoom.ChatRoomCreateRequestDTO;
import com.chat.dto.chatRoom.ChatRoomDTO;
import com.chat.dto.chatRoom.ChatRoomUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomsService {

    private final ChatRoomsDAO chatRoomsDAO;

    public int create(ChatRoomCreateRequestDTO requestDTO) {
        return chatRoomsDAO.create(requestDTO);
    }

    public int update(ChatRoomUpdateRequestDTO requestDTO) {
        return chatRoomsDAO.update(requestDTO);
    }

    public int delete(int id) {
        return chatRoomsDAO.delete(id);
    }

    public List<ChatRoomDTO> selectAll() {
        return chatRoomsDAO.selectAll();
    }

    public List<ChatRoomDTO> selectByCategory(String category) {
        return chatRoomsDAO.selectByCategory(category);
    }

    public ChatRoomDTO selectById(int id) {
        return chatRoomsDAO.selectById(id);
    }

    public ChatRoomDTO selectByLocation(String location) {
        return chatRoomsDAO.selectByLocation(location);
    }
}
