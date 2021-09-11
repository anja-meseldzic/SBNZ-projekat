package sbnz.integracija.example.dto;

public class SearchDTO {

	private String cuisineType;
	private String delivery;
	private String price;
	private String dishType;
	private String foodType;
	private String searchTerm;
	
	public SearchDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SearchDTO(String cuisineType, String delivery, String price, String dishType, String foodType,
			String searchTerm) {
		super();
		this.cuisineType = cuisineType;
		this.delivery = delivery;
		this.price = price;
		this.dishType = dishType;
		this.foodType = foodType;
		this.searchTerm = searchTerm;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDishType() {
		return dishType;
	}

	public void setDishType(String dishType) {
		this.dishType = dishType;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
	
	
	
}
