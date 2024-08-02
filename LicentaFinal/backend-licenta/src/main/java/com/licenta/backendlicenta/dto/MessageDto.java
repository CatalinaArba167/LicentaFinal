package com.licenta.backendlicenta.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MessageDto {
    private UUID id;
    private UUID senderId;
    private UUID chatId;
    private String content;
    private LocalDateTime sentAt;
}
