import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { RestaurantMenuComponent } from './components/restaurant-menu/restaurant-menu.component';
import { RouteGuardService } from './guards/route-guard.service';

const routes: Routes = [
  { path: '', component: LandingPageComponent, canActivate: [RouteGuardService], data: { expectedRoles: [] } },
  { path: 'login', component: LoginComponent, canActivate: [RouteGuardService], data: { expectedRoles: [] } },
  { path: 'register', component: RegistrationComponent, canActivate: [RouteGuardService], data: { expectedRoles: [] } },
  { path: 'home', component: HomePageComponent, canActivate: [RouteGuardService], data: { expectedRoles: ['REGULAR_USER'] } },
  { path: 'restaurant-menu/:restaurantName', component: RestaurantMenuComponent, canActivate: [RouteGuardService], data: { expectedRoles: ['REGULAR_USER'] } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
