import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Dish } from 'src/app/model/dish';
import { RestaurantDto } from 'src/app/model/restaurant-dto';
import { RestaurantService } from 'src/app/service/restaurant.service';
import { MatTable } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

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
  restaurant: RestaurantDto = new RestaurantDto();
  restaurantName: string; 
  displayedColumns: string[] = ['Name', 'Price', 'Dishtype', 'Foodtype', 'action'];
  displayedColumns1: string[] = ['Name', 'Price',  'action'];
  discountPercentage: string = '';
  order: Dish[] = [];
  totalPrice: number = 0;
  discountedPrice : number = 0;
  category : string = "";

  

  constructor(private route: ActivatedRoute, private restaurantService : RestaurantService, private router : Router, private snackBar: MatSnackBar) { }

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
   
    this.restaurantService.calculateOrderPrice(order, this.restaurantName).subscribe(data => {
      
      this.discountedPrice = Number(data.split("-")[1]);
      this.category = data.split("-")[0];
      if (this.category == 'SILVER') {
        this.discountPercentage = '5%'
      }
      else if (this.category == 'GOLD') {
        this.discountPercentage = '10%'
      }
      else if (this.category == 'PLATINUM') {
        this.discountPercentage = '15%'
      }
      console.log(this.category);
      console.log(data);
    }
    );

  }
  orderSave(){
    this.discountedPrice = 0;
    this.order = [];
    this.totalPrice = 0;
    this.snackBar.open("Thank you for your order", "OK");
  }

}
