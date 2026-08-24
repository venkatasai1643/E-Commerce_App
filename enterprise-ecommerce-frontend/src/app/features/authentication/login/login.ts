import { Component } from '@angular/core';
import {Router,RouterLink} from '@angular/router';
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
  constructor(private authService: AuthService,private router:Router) {}
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

        sessionStorage.setItem('token',response.token);
        sessionStorage.setItem('role',response.role);

        if(response.role==='CUSTOMER'){
          this.router.navigate(['/customer']);
        }
        else if(response.role==='SELLER'){
          this.router.navigate(['/seller']);
        }
         else if(response.role==='DELIVERY_PARTNER'){
          this.router.navigate(['/delivery']);
        }
         else if(response.role==='ADMIN'){
          this.router.navigate(['/admin']);
        }
      },

      error: (error: any) => {
        console.error('Login failed:', error);
      }

    });
  }
}
