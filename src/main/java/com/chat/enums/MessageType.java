package com.chat.enums;

import lombok.Getter;

@Getter
public enum MessageType {

    // 메시지  타입 : 입장, 채
    ENTER("입장"),
    TALK("채팅");

    private final String description;

    MessageType(String description) {
        this.description = description;
    }
}
