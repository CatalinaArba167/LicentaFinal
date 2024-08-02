package com.licenta.backendlicenta.dto;

import com.licenta.backendlicenta.domain.User;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChatDto {
    private UUID chatId;

    private User user1;

    private User user2;

    private List<MessageDto> messageList;
}
