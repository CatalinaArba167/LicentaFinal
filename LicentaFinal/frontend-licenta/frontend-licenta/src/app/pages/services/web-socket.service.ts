import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import { Client, Message, Stomp } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import {environment} from "../../../environments/environment";
import {AuthenticationService} from "../../core/services/authentication.service";
import {StorageKeys} from "../../globals/storage-keys";
@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private client: Client;
  private messageSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  private isConnected: boolean = false;

  constructor(private authService: AuthenticationService) {
    this.client = new Client({
      brokerURL: undefined,
      webSocketFactory: () => new SockJS(`${environment.apiUrl}/ws`),
      debug: (msg: string) => console.log(msg),
      connectHeaders: {
        Authorization: `Bearer ${localStorage.getItem(StorageKeys.TOKEN)}`}
    });

    this.client.onConnect = this.onConnect.bind(this);
    this.client.onStompError = this.onError.bind(this);

    this.activateConnection();
  }


  private activateConnection() {
    if (!this.isConnected) {
      this.client.activate();
    }
  }

  private onConnect(frame: any) {
    // this.isConnected = true;
    // this.client.subscribe('/topic/private', (message: Message) => {
    //   this.messageSubject.next(JSON.parse(message.body));
    // });
  }

  private onError(error: any) {
    this.isConnected = false;
  }

  sendMessage(destination: string, body: any) {
    this.client.publish({ destination, body: JSON.stringify(body) });
  }

  getMessages(): Observable<any> {
    return this.messageSubject.asObservable();
  }

  addUser(username: string) {
    this.sendMessage('/app/chat.addUser', { sender: username });
  }
}
