import {User} from "./authentication.types";
import {Chat} from "./chat.types";

export interface Message {
  id?: string;
  senderId: string;
  chatId: string;
  content: string;
  sentAt: string;
  isOutgoing?: boolean;
}
