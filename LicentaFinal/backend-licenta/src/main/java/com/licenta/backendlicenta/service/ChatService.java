package com.licenta.backendlicenta.service;

import com.licenta.backendlicenta.domain.Chat;
import com.licenta.backendlicenta.domain.Message;
import com.licenta.backendlicenta.domain.User;
import com.licenta.backendlicenta.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final UserService userService;

    public Optional<Chat> findOrCreateChat(UUID user1Id, UUID user2Id) {
        Optional<Chat> chat = chatRepository.findByUser1IdAndUser2Id(user1Id, user2Id);
        if (chat.isPresent()) {
            return chat;
        }

        chat = chatRepository.findByUser2IdAndUser1Id(user2Id, user1Id);
        if (chat.isPresent()) {
            return chat;
        }

        Chat newChat = new Chat();
        newChat.setId(UUID.randomUUID());
        newChat.setUser1(userService.findByID(user1Id));
        newChat.setUser1(userService.findByID(user2Id));
        return Optional.of(chatRepository.save(newChat));
    }

    public List<Chat> getUserChatsSortedByLastMessage(UUID userId) {
        User user = userService.findByID(userId);
        List<Chat> chats = chatRepository.findAllByUserSortedByLastMessage(user);

        return chats.stream()
                .peek(chat -> {
                    List<Message> mostRecentMessage = chat.getMessageList().stream()
                            .sorted((m1, m2) -> m2.getSentAt().compareTo(m1.getSentAt()))
                            .limit(1)
                            .collect(Collectors.toList());
                    chat.setMessageList(mostRecentMessage);
                })
                .collect(Collectors.toList());
    }
}
