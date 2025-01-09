import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class AppService {
  private socket: WebSocket;
  private messages: Subject<string> = new Subject<string>();

  connect() {
    this.socket = new WebSocket('ws://localhost:8080/ws/notification');

    this.socket.onopen = () => {
      console.log('WebSocket connection established');
    };

    this.socket.onmessage = (event) => {
      console.log('Message received: ', event.data);
      this.messages.next(event.data);
    };

    this.socket.onclose = () => {
      console.log('WebSocket connection closed');
    };
  }

  getMessages(): Observable<string> {
    return this.messages.asObservable();
  }
}
