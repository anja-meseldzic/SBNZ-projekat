import { Component, OnInit } from '@angular/core';
import { RestaurantDto } from 'src/app/model/restaurant-dto';
import { SearchDTO } from 'src/app/model/search-dto';
import { RestaurantService } from 'src/app/service/restaurant.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  search: SearchDTO = new SearchDTO();
  cuisines = [];
  dishes = [];
  prices = [];
  deliveries =[];
  food =[];
  restaurants : RestaurantDto[];
  constructor(private restaurantService : RestaurantService) { }

  ngOnInit(): void {
    this.restaurantService.getCuisineType().subscribe(data => {
      this.cuisines = data;
    });
    this.restaurantService.getDeliveryType().subscribe(data => {
      this.deliveries = data;
    });
    this.restaurantService.getDishType().subscribe(data => {
      this.dishes = data;
    });
    this.restaurantService.getFoodType().subscribe(data => {
      this.food = data;
    });
    this.restaurantService.getPriceType().subscribe(data => {
      this.prices = data;
    });
    this.restaurantService.getRestaurants().subscribe(
      data => this.restaurants = data
    )
  }

  runSearch(){
    console.log(this.search);
  }



}
