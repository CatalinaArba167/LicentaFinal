package com.licenta.backendlicenta.controller;

import com.licenta.backendlicenta.domain.Message;
import com.licenta.backendlicenta.dto.MessageDto;
import com.licenta.backendlicenta.mapper.MessageMapper;
import com.licenta.backendlicenta.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @GetMapping("/chat/{chatId}")
    public ResponseEntity<List<MessageDto>> getMessages(@PathVariable UUID chatId) {
        return ResponseEntity.ok(messageMapper.cascadeToDto(messageService.getMessagesByChatId(chatId)));
    }

    @PostMapping
    public ResponseEntity<Message> sendMessage(
            @RequestParam UUID senderId,
            @RequestParam UUID chatId,
            @RequestParam String content) {
        return ResponseEntity.ok(messageService.sendMessage(senderId, chatId, content));
    }
}

