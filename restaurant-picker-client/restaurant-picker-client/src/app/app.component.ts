import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './service/auth.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Restaurant picker';
  showButton : boolean = false;
  constructor(private authService : AuthService, private router : Router){}
  
  ngOnInit(): void {
    this.loggedIn();
  }

  logOut(){
    this.authService.logOut();
    this.router.navigate(['/']);
    this.showButton = false;
  }
  loggedIn(){
    this.authService.isLoggedin();
    this.showButton = true
  }
}
