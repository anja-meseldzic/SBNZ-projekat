package sbnz.integracija.example.dto;

public class DishDTO {
	private long id;
	private String name;
	private double price;
	private String dishType;
	private String foodType;

	public DishDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DishDTO(long id,String name, double price, String dishType, String foodType) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.dishType = dishType;
		this.foodType = foodType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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

}
