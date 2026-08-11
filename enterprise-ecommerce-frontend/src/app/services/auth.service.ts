import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface RegisterRequest {
  firstName: string;
  lastName: string;
  email: string;
  phoneNumber: string;
  password: string;
  role: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) {}

  register(user: RegisterRequest): Observable<string> {
    return this.http.post<string>(
      `${this.apiUrl}/api/save_details`,
      user
    );
  }

  login(user: { email: string; password: string }): Observable<string> {
    return this.http.post<string>(
      `${this.apiUrl}/api/login`,
      user
    );
  }
}