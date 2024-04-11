package com.chat.dto.chatRoom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomUpdateRequestDTO {

    private Long id;
    private String roomTitle;
    private String roomDescription;
    private Integer maxAmount;
}
