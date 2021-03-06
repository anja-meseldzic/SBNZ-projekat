import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Dish } from '../model/dish';
import { RestaurantDto } from '../model/restaurant-dto';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  
  constructor(private http : HttpClient) { }

  public getRestaurants() : Observable<RestaurantDto[]>{
    return this.http.get<RestaurantDto[]>(environment.baseUrl + 'restaurants/all');
  }
  public getCuisineType(): Observable<string[]>{
    return this.http.get<string[]>(environment.baseUrl + 'restaurants/cuisineTypes');
  }
  public getDishType(): Observable<string[]>{
    return this.http.get<string[]>(environment.baseUrl + 'restaurants/dishTypes');
  }
  public getFoodType(): Observable<string[]>{
    return this.http.get<string[]>(environment.baseUrl + 'restaurants/foodTypes');
  }
  public getPriceType(): Observable<string[]>{
    return this.http.get<string[]>(environment.baseUrl + 'restaurants/priceTypes');
  }
  public getDeliveryType(): Observable<string[]>{
    return this.http.get<string[]>(environment.baseUrl + 'restaurants/deliveryTypes');
  }
  public getByName(name : string) : Observable<RestaurantDto>{
    return this.http.get<RestaurantDto>(environment.baseUrl + 'restaurants/getOne/' + name);
  }
  public calculateOrderPrice(order : Dish[],name : string) : Observable<any>{
    return this.http.post(environment.baseUrl + 'restaurants/calculatePrice/' + name, order,{responseType: 'text'});
  }


}
