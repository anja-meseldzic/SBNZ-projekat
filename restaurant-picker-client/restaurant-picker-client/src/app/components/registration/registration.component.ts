import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserDTO } from 'src/app/model/user-dto';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  name = '';
  surname ='';
  email ='';
  password = '';
  
  constructor(private router : Router, private authService : AuthService,private snackBar : MatSnackBar) { }

  ngOnInit(): void {
  }
  cancel(){
    this.router.navigate(['/']);
  }
  register(name, surname, email, password){
    var user : UserDTO = new UserDTO(name, surname, email, password);
    this.authService.register(user).subscribe(
      (data) => {
      this.showMessage('You have successfully registered.');
      this.router.navigate(['/login'])},
      (error) => this.showMessage(error.error.message)
      
    )
  }

  showMessage(message : string){
    this.snackBar.open(message, "Okay", {duration: 3000})
  }
}
