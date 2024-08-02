package com.licenta.backendlicenta.controller;

import com.licenta.backendlicenta.domain.Message;
import com.licenta.backendlicenta.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/private")
    public Message sendMessage(Message message) {
        return messageService.sendMessage(message.getSender().getId(), message.getChat().getId(), message.getContent());
    }
}
