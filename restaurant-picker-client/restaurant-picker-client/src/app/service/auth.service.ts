import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserDTO } from '../model/user-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http : HttpClient) { }

  register(user : UserDTO) : Observable<any> {
    return this.http.post(environment.baseUrl + 'auth/register', user);
  }

  login(user : UserDTO) : Observable<any> {
    return this.http.post(environment.baseUrl + 'auth/login', user, {responseType : 'text'});
  }

  setLoggedIn(token : string){
    localStorage.setItem('token', token);
  }

  isLoggedin() : boolean{
    return localStorage.getItem('token') != null;
  }

  logOut(): boolean{
    if(this.isLoggedin()){
      localStorage.removeItem('token');
      return true;
    }
    return false;
    
  }
}
