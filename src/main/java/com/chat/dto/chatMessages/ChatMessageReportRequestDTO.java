package com.chat.dto.chatMessages;

import com.chat.enums.ChatReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageReportRequestDTO {

    private Long id;
    private ChatReport chatReport = ChatReport.YES;
    // 관리자만 신고가능하거나, 신고 테이블을 따로 만들어야함.
}
