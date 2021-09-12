package sbnz.integracija.example.model;


public class Reccommendation {

	private long id;
	private User user;
	private PriceCategory category;
	
	public Reccommendation(long id, User user, PriceCategory category) {
		super();
		this.id = id;
		this.user = user;
		this.category = category;
	}

	public Reccommendation() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PriceCategory getCategory() {
		return category;
	}

	public void setCategory(PriceCategory category) {
		this.category = category;
	}
	
	
	
}
