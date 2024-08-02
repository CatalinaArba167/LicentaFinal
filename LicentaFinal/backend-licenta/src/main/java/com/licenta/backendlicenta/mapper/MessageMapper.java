package com.licenta.backendlicenta.mapper;

import com.licenta.backendlicenta.domain.Message;
import com.licenta.backendlicenta.dto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageMapper {
    public MessageDto toDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .sentAt(message.getSentAt())
                .content(message.getContent())
                .senderId(message.getSender().getId())
                .chatId(message.getChat().getId())
                .build();
    }

    public List<MessageDto> cascadeToDto(List<Message> messageList) {
        return messageList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

}
