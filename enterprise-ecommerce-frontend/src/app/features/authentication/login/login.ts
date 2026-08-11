import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { subscribe } from 'diagnostics_channel';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  constructor(private authService: AuthService) {}
  email:string='';
  password:string=''; 

  loginUser() {
    console.log('Login button clicked');
   
    const user= { 
      email: this.email,
      password: this.password
    };
     this.authService.login(user).subscribe({
      next: (response: any) => {
        console.log('Login successful:', response);
      },

      error: (error: any) => {
        console.error('Login failed:', error);
      }

    });
  }
}
