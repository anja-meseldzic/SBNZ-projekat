import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RouteGuardService {

  constructor(public authService: AuthService, public router: Router) { }
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles : string[] = route.data.expectedRoles;    
    if(!this.authService.isLoggedin() && expectedRoles.length > 0) {
      this.router.navigate(['./']);
      return false;
    }
    return true;
  }
}
