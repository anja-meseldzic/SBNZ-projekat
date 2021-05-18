package sbnz.integracija.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "foodType", nullable = false)
	private FoodType foodType;
	
	@Column(name = "dishType", nullable =  false)
	private DishType dishType;
	
	@Column(name = "priceCategory", nullable = false)
	private PriceCategory priceCategory;

	public Dish() {}
	
	
	public Dish(long id, String name, FoodType foodType, DishType dishType, PriceCategory priceCategory) {
		super();
		this.id = id;
		this.name = name;
		this.foodType = foodType;
		this.dishType = dishType;
		this.priceCategory = priceCategory;
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

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public DishType getDishType() {
		return dishType;
	}

	public void setDishType(DishType dishType) {
		this.dishType = dishType;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}
	
	
}
