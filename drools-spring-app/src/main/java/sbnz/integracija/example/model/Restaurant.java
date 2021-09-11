package sbnz.integracija.example.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "location")
	private Location location;
	
	@Column(name = "delivery")
	private Delivery delivery;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Dish> menu;
	
	@Column(name = "priceCategory")
	private PriceCategory priceCategory;
	
	@Column(name = "cuisineType")
	private CuisineType cusine;

	public Restaurant() {}
	
	public Restaurant(long id, String name, Location location, Delivery delivery, List<Dish> menu,PriceCategory category, CuisineType cuisine) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.delivery = delivery;
		this.menu = menu;
		this.priceCategory = category;
		this.cusine = cuisine;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public List<Dish> getMenu() {
		return menu;
	}

	public void setMenu(List<Dish> menu) {
		this.menu = menu;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

	public CuisineType getCusine() {
		return cusine;
	}

	public void setCusine(CuisineType cusine) {
		this.cusine = cusine;
	}
	
	
}
