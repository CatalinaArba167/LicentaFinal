import {Component, OnInit} from '@angular/core';
import {Chat} from "../../shared/types/chat.types";
import {Message} from "../../shared/types/message.types";
import {WebSocketService} from "../services/web-socket.service";
import {HttpClient} from "@angular/common/http";

import {ChatService} from "../services/chat.service";
import {AuthenticationService} from "../../core/services/authentication.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent  {
  // connected: boolean = false;
  // // currentUserId: string = '1227c7dc-a550-495e-8dd2-f9cf7bfaed85';
  // // recipientId: string = '88cf4c33-72ad-409c-aa0a-ef6606f4e69b';
  //
  // chats: Chat[] = [];
  // messages: Message[] = [];
  // selectedChat: Chat | null = null;
  // newMessage = '';
  //
  //
  // constructor(private webSocketService: WebSocketService, private http: HttpClient, private authService: AuthenticationService,private chatService: ChatService) {
  // }
  //
  // ngOnInit(): void {
  //   this.loadUserChats();
  //   this.webSocketService.getMessages().subscribe((message: Message) => {
  //     this.updateChatList(message);
  //   });
  // }
  // getOtherUser(chat: Chat) {
  //   return chat.user1.id === this.authService.getUserId()! ? chat.user2 : chat.user1;
  // }
  // private loadUserChats(): void {
  //   this.chatService.getUserChats().subscribe((chats: Chat[]) => {
  //     this.chats = chats;
  //     if (this.chats.length > 0) {
  //       this.selectChat(this.chats[0]);
  //     }
  //   });
  // }
  // selectChat(chat: Chat): void {
  //   this.selectedChat = chat;
  //   console.log(chat.id)
  //   this.chatService.getChatMessages(chat.id).subscribe((messages: Message[]) => {
  //     this.messages = messages;
  //   });
  // }
  // sendMessage(): void {
  //   if (this.newMessage.trim() && this.selectedChat) {
  //     const message: Message = {
  //       senderId: this.authService.getUserId()!,
  //       chatId: this.selectedChat.id,
  //       content: this.newMessage.trim(),
  //       sentAt: new Date().toISOString()
  //     };
  //     this.webSocketService.sendMessage('/app/chat.sendMessage', message);
  //     this.newMessage = '';
  //     this.updateChatList(message);
  //   }
  // }
  //
  // private updateChatList(message: Message): void {
  //   const chat = this.chats.find(c => c.id === message.chatId);
  //   if (chat) {
  //     chat.messageList.push(message);
  //     // Move chat to the top of the list
  //     this.chats = this.chats.filter(c => c.id !== chat.id);
  //     this.chats.unshift(chat);
  //
  //     // If this chat is selected, update the messages
  //     if (this.selectedChat && this.selectedChat.id === chat.id) {
  //       this.messages = chat.messageList;
  //     }
  //   } else {
  //     this.loadUserChats(); // Reload chats if a new chat appears
  //   }
  // }
}
