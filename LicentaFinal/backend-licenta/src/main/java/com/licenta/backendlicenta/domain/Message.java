package com.licenta.backendlicenta.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@SuperBuilder
public class Message extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne()
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    private String content;

    private LocalDateTime sentAt;
}
