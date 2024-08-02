package com.licenta.backendlicenta.mapper;

import com.licenta.backendlicenta.domain.Chat;
import com.licenta.backendlicenta.dto.ChatDto;
import com.licenta.backendlicenta.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatMapper {

    public ChatDto toDto(Chat chat, List<MessageDto> messageDtoList) {
        return ChatDto.builder()
                .chatId(chat.getId())
                .user1(chat.getUser1())
                .user2(chat.getUser2())
                .messageList(messageDtoList)
                .build();
    }


}
