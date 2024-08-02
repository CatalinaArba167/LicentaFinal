package com.licenta.backendlicenta.controller;


import com.licenta.backendlicenta.domain.Chat;
import com.licenta.backendlicenta.dto.ChatDto;
import com.licenta.backendlicenta.mapper.ChatMapper;
import com.licenta.backendlicenta.mapper.MessageMapper;
import com.licenta.backendlicenta.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;
    private final ChatMapper chatMapper;
    private final MessageMapper messageMapper;


    @GetMapping("/private/{user1Id}/{user2Id}")
    public ResponseEntity<Chat> getOrCreateChat(@PathVariable UUID user1Id, @PathVariable UUID user2Id) {
        Optional<Chat> chat = chatService.findOrCreateChat(user1Id, user2Id);
        return chat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ChatDto>> getUserChats(@PathVariable UUID userId) {
        List<Chat> chats = chatService.getUserChatsSortedByLastMessage(userId);
        return new ResponseEntity<>(chats.stream().map(chat -> chatMapper
                .toDto(chat, messageMapper.cascadeToDto(chat.getMessageList()))).toList(),
                HttpStatus.OK);
    }


}

