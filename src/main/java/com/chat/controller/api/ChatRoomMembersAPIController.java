package com.chat.controller.api;

import com.chat.dto.chatRoomMembers.ChatRoomMembersDTO;
import com.chat.service.ChatRoomMembersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatroom-members")
public class ChatRoomMembersAPIController {

    private final ChatRoomMembersService chatRoomMembersService;

    // 채팅방 참여
    @PostMapping("/join")
    public ResponseEntity<?> joinChatRoom(@RequestBody ChatRoomMembersDTO chatRoomMembersDTO) {
        if (chatRoomMembersService.join(chatRoomMembersDTO) == 0) {
            return ResponseEntity.badRequest().body("채팅방 참여에 실패했습니다.");
        }
        return ResponseEntity.ok().body("성공적으로 채팅방에 참여했습니다.");
    }

    // 채팅방 탈퇴
    @PostMapping("/leave")
    public ResponseEntity<?> leaveChatRoom(@RequestBody ChatRoomMembersDTO chatRoomMembersDTO) {
        if (chatRoomMembersService.leave(chatRoomMembersDTO) == 0) {
            return ResponseEntity.badRequest().body("채팅방 탈퇴에 실패했습니다.");
        }

        return ResponseEntity.ok().body("채팅방에서 성공적으로 탈퇴했습니다.");
    }

    // 특정 채팅방의 모든 회원 조회
    @GetMapping("/room/{chatRoomId}")
    public ResponseEntity<List<ChatRoomMembersDTO>> getMembersByChatRoomId(@PathVariable Long chatRoomId) {
        List<ChatRoomMembersDTO> members = chatRoomMembersService.findAllByChatRoomId(chatRoomId);
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }

    // 특정 사용자가 참여한 모든 채팅방의 회원 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ChatRoomMembersDTO>> getMembersByUserId(@PathVariable String userId) {
        List<ChatRoomMembersDTO> members = chatRoomMembersService.findAllByUserId(userId);
        if (members.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(members);
    }
}
