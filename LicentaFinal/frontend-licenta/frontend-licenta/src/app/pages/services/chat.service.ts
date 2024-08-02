import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Chat} from "../../shared/types/chat.types";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {AuthenticationService} from "../../core/services/authentication.service";
import {Message} from "../../shared/types/message.types";


@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http: HttpClient, private authService: AuthenticationService) {}

  getUserChats(): Observable<Chat[]> {
    const userId = this.authService.getUserId();
    return this.http.get<Chat[]>(`${environment.apiUrl}/chat/${userId}`);
  }

  getChatMessages(chatId: string): Observable<Message[]> {
    return this.http.get<Message[]>(`${environment.apiUrl}/messages/chat/${chatId}`);
  }
}
