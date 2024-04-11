package com.chat.enums;

import lombok.Getter;

@Getter
public enum ChatReport {

    YES("신고된 채팅"),
    No("정상 채팅");

    private final String description;

    ChatReport(String description) {
        this.description = description;
    }
}
