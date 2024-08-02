package com.licenta.backendlicenta.repository;

import com.licenta.backendlicenta.domain.Chat;
import com.licenta.backendlicenta.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    Optional<Chat> findByUser1IdAndUser2Id(UUID user1Id, UUID user2Id);

    Optional<Chat> findByUser2IdAndUser1Id(UUID user2Id, UUID user1Id);

    @Query("SELECT c FROM Chat c WHERE c.user1 = :user OR c.user2 = :user ORDER BY " +
            "(SELECT MAX(m.sentAt) FROM Message m WHERE m.chat = c) DESC")
    List<Chat> findAllByUserSortedByLastMessage(@Param("user") User user);
}
