package com.chat.dto.chatRoom;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomCreateRequestDTO {

    private String roomTitle;
    private String roomDescription;
    private Integer maxAmount;
    private String location;
    private LocalDateTime creationDate = LocalDateTime.now();
    private String category;
    private String leaderId;
}
