import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-register',
  imports: [RouterLink, FormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  selectedRole: string = 'CUSTOMER';

  user = {
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    password: '',
    role: 'CUSTOMER'
  };

  confirmPassword: string = '';

  constructor(private authService: AuthService) {}

  registerUser() {
    console.log('button clicked');

    // Set selected role
    this.user.role = this.selectedRole;

    // Check password
    if (this.user.password !== this.confirmPassword) {
      console.error('Passwords do not match');
      return;
    }

    console.log('Sending data:', this.user);

    this.authService.register(this.user).subscribe({

      next: (response) => {
        console.log('Registration successful:', response);
      },

      error: (error) => {
        console.error('Registration failed:', error);
      }

    });
  }
}