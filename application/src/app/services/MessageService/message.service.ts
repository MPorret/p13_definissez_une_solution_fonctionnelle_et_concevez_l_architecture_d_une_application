import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from '../../interfaces/message.interface';
import { catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MessageService {

  constructor(private httpClient: HttpClient) { }

  getAllMessages(reclamationId: number) {
    return this.httpClient.get<Message[]>(`/api/chat/reclamation/${reclamationId}/messages`)
      .pipe(
        catchError(err => {
          console.error('Failed to load messages', err);
          return of([]); // return empty array on error
        })
      );
  }
}
