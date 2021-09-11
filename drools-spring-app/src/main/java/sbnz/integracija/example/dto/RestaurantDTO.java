package sbnz.integracija.example.dto;

import java.util.List;

public class RestaurantDTO {

	private String name;
	private String cuisine;
	
	private String delivery;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCuisine() {
		return cuisine;
	}
	public void setCuisine(String cuisine) {
		this.cuisine = cuisine;
	}
	
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public RestaurantDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RestaurantDTO(String name, String cuisine, String delivery) {
		super();
		this.name = name;
		this.cuisine = cuisine;
		
		this.delivery = delivery;
	}
	
	
}
