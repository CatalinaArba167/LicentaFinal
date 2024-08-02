package com.licenta.backendlicenta.service;


import com.licenta.backendlicenta.domain.Message;
import com.licenta.backendlicenta.repository.ChatRepository;
import com.licenta.backendlicenta.repository.MessageRepository;
import com.licenta.backendlicenta.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public Message sendMessage(UUID senderId, UUID chatId, String content) {
        Message message = new Message();
        message.setId(UUID.randomUUID());
        message.setSender(userRepository.findById(senderId).get());
        message.setChat(chatRepository.findById(chatId).get());
        message.setContent(content);
        message.setSentAt(LocalDateTime.now());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByChatId(UUID chatId) {
        return messageRepository.findByChatIdOrderBySentAtAsc(chatId);
    }
}
