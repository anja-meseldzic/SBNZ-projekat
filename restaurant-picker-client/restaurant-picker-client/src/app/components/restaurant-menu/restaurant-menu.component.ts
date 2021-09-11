import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Dish } from 'src/app/model/dish';
import { RestaurantDto } from 'src/app/model/restaurant-dto';
import { RestaurantService } from 'src/app/service/restaurant.service';
import { MatTable } from '@angular/material/table';

@Component({
  selector: 'app-restaurant-menu',
  templateUrl: './restaurant-menu.component.html',
  styleUrls: ['./restaurant-menu.component.css']
})
export class RestaurantMenuComponent implements OnInit {

  @ViewChild('orderTable') myTable: MatTable<any>; /*not exatly sure what the type should be set too */

    editDataMethod(){
        /* data handling logic */
        this.myTable.renderRows();

}
  restaurant: RestaurantDto;
  restaurantName: string; 
  displayedColumns: string[] = ['Name', 'Price', 'Dishtype', 'Foodtype', 'action'];
  displayedColumns1: string[] = ['Name', 'Price',  'action'];
  order: Dish[] = [];
  totalPrice: number = 0;

  

  constructor(private route: ActivatedRoute, private restaurantService : RestaurantService) { }

  ngOnInit(): void {
    this.restaurantName = this.route.snapshot.paramMap.get('restaurantName');
    this.restaurantService.getByName(this.restaurantName).subscribe(
      data => {this.restaurant = data;}
     
    )

  }
  addToOrder(dish: Dish) {
    this.order.push(dish);
    this.totalPrice += dish.price;
    this.myTable.renderRows(); 
  }
  removeFromOrder(dish : Dish){
    const index: number = this.order.indexOf(dish);
    if (index !== -1) {
        this.order.splice(index, 1);
    }  
    this.totalPrice -=dish.price;
    this.myTable.renderRows(); 

  }
  goToCheckout(order : Dish[]){
    this.restaurantService.calculateOrderPrice(order, this.restaurantName).subscribe(data => console.log(data));
  }

}
