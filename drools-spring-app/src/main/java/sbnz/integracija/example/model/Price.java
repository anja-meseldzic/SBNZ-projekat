package sbnz.integracija.example.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Dish dish;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Restaurant restaurant;
	
	@Column(name = "priceCategory", nullable = false)
	private PriceCategory priceCategory;

	public Price() {}
	
	
	public Price(long id, double price, Dish dish, Restaurant restaurant,PriceCategory category) {
		super();
		this.id = id;
		this.price = price;
		this.dish = dish;
		this.restaurant = restaurant;
		this.priceCategory = category;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Dish getDish() {
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}



	public PriceCategory getPriceCategory() {
		return priceCategory;
	}



	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}
	
	
	
}
