import {Message} from "./message.types";
import {User} from "./authentication.types";

export interface ChatMessage {
  sender: string;
  content: string;
  chatId: string;
  senderId: string;
  type: 'CHAT' | 'JOIN' | 'LEAVE';
}
export interface Chat {
  id: string;
  user1: User;
  user2: User;
  messageList: Message[];
}
