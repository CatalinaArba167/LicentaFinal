package com.licenta.backendlicenta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Table(name = "chat")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@SuperBuilder
public class Chat extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne()
    @JoinColumn(name = "user2_id")
    private User user2;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messageList;
}
