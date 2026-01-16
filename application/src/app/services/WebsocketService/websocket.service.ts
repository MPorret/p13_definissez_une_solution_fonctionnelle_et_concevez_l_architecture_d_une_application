import { Injectable } from '@angular/core';
import { Client, IMessage } from '@stomp/stompjs';

@Injectable({ providedIn: 'root' })
export class WebsocketService {

  private client!: Client;

  connect(reclamationId: number, onMessage: (msg: any) => void) {
    this.client = new Client({
      brokerURL: 'ws://localhost:3001/chat',
      reconnectDelay: 1000,
      onConnect: () => {
        this.client.subscribe(
          `/topic/reclamation/${reclamationId}`,
          (message: IMessage) => {
            onMessage(JSON.parse(message.body));
          }
        );
      }
    });

    this.client.activate();
  }

  sendMessage(reclamationId: number, payload: any) {
    if (!this.client.active) {
      console.warn('STOMP client not connected yet!');
      return;
    }
    this.client.publish({
      destination: `/app/reclamation/${reclamationId}`,
      body: JSON.stringify(payload)
    });
  }
}
