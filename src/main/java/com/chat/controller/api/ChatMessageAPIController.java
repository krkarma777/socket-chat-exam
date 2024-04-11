package com.chat.controller.api;

import com.chat.dto.chatMessages.ChatMessageCreateRequestDTO;
import com.chat.dto.chatMessages.ChatMessageReportRequestDTO;
import com.chat.dto.chatMessages.ChatMessageResponseDTO;
import com.chat.service.ChatMessagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-message")
public class ChatMessageAPIController {

    private final ChatMessagesService chatMessagesService;

    // 채팅 메시지 생성
    @PostMapping
    public ResponseEntity<?> createChatMessage(@RequestBody ChatMessageCreateRequestDTO requestDTO) {
        if (chatMessagesService.create(requestDTO) == 0) {
            return ResponseEntity.badRequest().body("채팅 메시지 생성에 실패하였습니다.");
        }
        return ResponseEntity.ok().body("채팅 메시지가 성공적으로 생성되었습니다.");
    }

    // 채팅 메시지 신고
    @PatchMapping("/report")
    public ResponseEntity<?> reportChatMessage(@RequestBody ChatMessageReportRequestDTO requestDTO) {
        if (chatMessagesService.report(requestDTO) == 0) {
            return ResponseEntity.badRequest().body("채팅 메시지 신고 처리에 실패하였습니다.");
        }
        return ResponseEntity.ok().body("채팅 메시지 신고가 접수되었습니다.");
    }

    // 특정 채팅방의 채팅 메시지 조회
    @GetMapping("/room/{chatRoomId}")
    public ResponseEntity<List<ChatMessageResponseDTO>> getChatMessagesByRoomId(@PathVariable String chatRoomId) {
        List<ChatMessageResponseDTO> messages = chatMessagesService.chatMessagesByRoomId(chatRoomId);
        if (messages.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(messages);
    }
}
