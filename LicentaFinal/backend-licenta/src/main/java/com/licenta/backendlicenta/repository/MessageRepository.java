package com.licenta.backendlicenta.repository;


import com.licenta.backendlicenta.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findByChatIdOrderBySentAtAsc(UUID chatId);
}
