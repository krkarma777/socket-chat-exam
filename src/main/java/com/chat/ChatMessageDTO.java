package com.chat;

import com.chat.enums.ChatReport;
import com.chat.enums.MessageType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {

    /* 웹소켓 통신 시 보내는 양식

        {
        "messageType":"TALK", // ENTER, TALK
        "chatRoomId":1, // 채팅방 번호
        "senderId":100, // 메세지 전송자의 UserId
        "message":"hello" // 메세지 내용
        "reported":"YES" // 신고내용
        }

    */

    private MessageType messageType; // 메시지 타입
    private LocalDateTime sendTime;
    private Long chatRoomId; // 방 번호
    private Long senderId; // 채팅을 보낸 사람
    private String message; // 메시지
    private ChatReport reported;
}

