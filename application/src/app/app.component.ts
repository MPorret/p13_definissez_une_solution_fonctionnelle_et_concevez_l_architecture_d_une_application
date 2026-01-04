import { Component, inject, OnDestroy, OnInit, signal } from '@angular/core';
import { WebsocketService } from './services/WebsocketService/websocket.service';
import { Message } from './interfaces/message.interface';
import { Subscription } from 'rxjs';
import { MessageService } from './services/MessageService/message.service';
import { CommonModule } from '@angular/common';
import { UserService } from './services/UserService/user.service';
import { User } from './interfaces/user.interface';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit, OnDestroy {

  private websocketService = inject(WebsocketService);
  private subscription: Subscription[] = [];
  
  users: User[] = [];

  messages = signal<Message[]>([]);

  reclamationId = 1; // POC hardcoded

  activeUserId = this.users[0]?.id || 1;

  newMessage: string = '';

  constructor(private messageService: MessageService, private userService: UserService) {}

  ngOnInit(): void {
    // Load history
    this.subscription.push(this.messageService.getAllMessages(this.reclamationId)
        .subscribe(msgs => this.messages.set(msgs)));

    this.subscription.push(this.userService.getUsers().subscribe(users => {
      this.users = users;
    }));

    // Connect STOMP
    this.websocketService.connect(
      this.reclamationId,
      (msg: Message) => this.messages.update(m => [...m, msg])
    );
  }

  ngOnDestroy(): void {
    if (this.subscription.length) {
      this.subscription.forEach(sub => sub.unsubscribe());
    }
  }

  sendMessage() {
    if (this.newMessage.trim()) {
      this.websocketService.sendMessage(this.reclamationId, {
        author_id: this.activeUserId,
        reclamation_id: this.reclamationId,
        message: this.newMessage
      });
      this.newMessage = '';
    }
  }

  switchConversation(userId: number) {
    this.activeUserId = userId;
  }
}
