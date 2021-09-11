import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterLink } from '@angular/router';
import { UserDTO } from 'src/app/model/user-dto';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = "";
  password = "";
  
  constructor(private router : Router, private authService : AuthService, private snackBar : MatSnackBar){ }
  
  ngOnInit(): void {
  }

  login(email, password){
    var user : UserDTO = new UserDTO("","","","");
    user.email = email;
    user.password = password;
    this.authService.login(user).subscribe(
      (data)=> {
      this.authService.setLoggedIn(data);
      this.showMessage("You have successfully logged in.");
      this.router.navigate(['/home'])},
      (error) => this.showMessage("User doesn't exist, or password is incorrect")
    )
  }
  cancel(){
    this.router.navigate(['/']);
  }

  showMessage(message : string){
    this.snackBar.open(message, "Okay", {duration: 3000})
  }
}
