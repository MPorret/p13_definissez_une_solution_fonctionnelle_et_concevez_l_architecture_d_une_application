import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../interfaces/user.interface';
import { catchError, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private httpClient: HttpClient) { }

  getUsers() {
    return this.httpClient.get<User[]>(`/api/users`)
      .pipe(
        catchError(err => {
          console.error('Failed to load users', err);
          return of([]); // return empty array on error
        })
      );
  }
  
}
