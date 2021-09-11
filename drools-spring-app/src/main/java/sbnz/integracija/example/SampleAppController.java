package sbnz.integracija.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.CuisineType;
import sbnz.integracija.example.model.Delivery;
import sbnz.integracija.example.model.Discount;
import sbnz.integracija.example.model.Dish;
import sbnz.integracija.example.model.DishType;
import sbnz.integracija.example.model.FoodType;
import sbnz.integracija.example.model.Order;
import sbnz.integracija.example.model.Price;
import sbnz.integracija.example.model.PriceCategory;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.DishRepository;
import sbnz.integracija.example.repository.PriceRepository;
import sbnz.integracija.example.repository.RestaurantRepository;
import sbnz.integracija.example.repository.UserRepository;

@RestController
public class SampleAppController {
	private static Logger log = LoggerFactory.getLogger(SampleAppController.class);

	private final KieContainer kieContainer;
	private UserRepository repository;
	private PriceRepository priceRepo;
	private RestaurantRepository resRepo;
	private DishRepository dishRepo;

	@Autowired
	public SampleAppController(UserRepository repo, KieContainer kieContainer, PriceRepository priceRepo,
			DishRepository dishRepo, RestaurantRepository resRepo) {
		log.info("Initialising a new session.");
		this.repository = repo;
		this.kieContainer = kieContainer;
		this.priceRepo = priceRepo;
		this.dishRepo = dishRepo;
		this.resRepo = resRepo;
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public void getUser() {

		KieSession kieSession = kieContainer.newKieSession();
		/*
		 * kieSession.getAgenda().getAgendaGroup("search").setFocus();
		 * kieSession.setGlobal("_foodType", FoodType.VEGAN);
		 * kieSession.setGlobal("_dishType", DishType.MAIN_COURSE);
		 * kieSession.setGlobal("_cuisine", CuisineType.HOME_CUISIEN);
		 * kieSession.setGlobal("_delivery", Delivery.GLOVO);
		 * kieSession.setGlobal("_price", PriceCategory.CHEAP);
		 * kieSession.setGlobal("_name", "pera1");
		 */

		Order o1 = new Order();
		Order o2 = new Order();
		Order o3 = new Order();

		List<User> users = repository.findAll();
		List<Restaurant> restaurants =  resRepo.findAll();
		List<Price> prices = priceRepo.findAll();
		List<Dish> dishes = dishRepo.findAll();
		
		for (Dish d : dishes) {
			kieSession.insert(d);
		}
		
		for (Price p : prices) {
			kieSession.insert(p);
		}
		
		for (Restaurant r : restaurants) {
			kieSession.insert(r);
		}

		for (User u : users) {
			kieSession.insert(u);
		}

		/*User u = repository.findById((long) 1);

		System.out.println(u.toString());

		o1.setUser(u);
		o2.setUser(u);
		o3.setUser(u);

		Discount dis = new Discount();

		Dish d1 = new Dish();
		Dish d2 = new Dish();
		Dish d3 = new Dish();

		Restaurant r1 = new Restaurant();
		Restaurant r2 = new Restaurant();
		Restaurant r3 = new Restaurant();
		Restaurant r4 = new Restaurant();
		Restaurant r5 = new Restaurant();

		d1.setId(0);
		d1.setDishType(DishType.MAIN_COURSE);
		d1.setFoodType(FoodType.VEGAN);
		d2.setId(1);
		d2.setDishType(DishType.MAIN_COURSE);
		d2.setFoodType(FoodType.HEALTHY_FOOD);
		d3.setId(2);

		o1.setRestaurant(r1);
		o2.setRestaurant(r5);
		o3.setRestaurant(r5);

		List<Dish> dishes = new ArrayList<Dish>();
		dishes.add(d1);
		dishes.add(d2);
		o1.setDishes(dishes);

		List<Dish> dishes1 = new ArrayList<Dish>();
		dishes1.add(d3);
		o2.setDishes(dishes1);

		o3.setDishes(dishes1);

		// dis.setRestaurant(r1);
		// dis.setDish(d1);
		// dis.setDiscount(20);

		r1.setId(0);
		r1.setName("pera");
		r1.setDelivery(Delivery.GLOVO);
		r1.setMenu(dishes);
		r1.setCusine(CuisineType.HOME_CUISIEN);
		r1.setPriceCategory(PriceCategory.CHEAP);

		r2.setId(1);
		r2.setName("pera1");
		r2.setDelivery(Delivery.GLOVO);
		r2.setMenu(dishes);
		r2.setCusine(CuisineType.HOME_CUISIEN);

		r3.setId(2);
		r3.setName("pera2");
		r3.setDelivery(Delivery.GLOVO);

		r4.setId(3);
		r4.setName("pera3");
		r5.setId(4);

		Price p1 = new Price();
		Price p2 = new Price();
		Price p3 = new Price();
		Price p4 = new Price();
		Price p5 = new Price();
		Price p6 = new Price();
		Price p7 = new Price();

		p1.setDish(d3);
		p1.setRestaurant(r1);
		p1.setPrice(400);

		p2.setDish(d3);
		p2.setRestaurant(r5);
		p2.setPrice(400);

		p3.setDish(d3);
		p3.setRestaurant(r2);
		p3.setPrice(600);

		p4.setDish(d1);
		p4.setRestaurant(r1);
		p4.setPrice(400);

		p5.setDish(d2);
		p5.setRestaurant(r1);
		p5.setPrice(400);

		p6.setDish(d2);
		p6.setRestaurant(r2);
		p6.setPrice(600);

		p7.setDish(d1);
		p7.setRestaurant(r2);
		p7.setPrice(600);

		kieSession.insert(o1);
		// kieSession.insert(o2);
		// kieSession.insert(o3);
		// kieSession.insert(d3);
		kieSession.insert(d2);
		kieSession.insert(d1);
		kieSession.insert(r1);
		// kieSession.insert(r2);
		// kieSession.insert(r5);
		// kieSession.insert(p1);
		// kieSession.insert(p2);
		// kieSession.insert(p3);
		kieSession.insert(p4);
		kieSession.insert(p5);
		// kieSession.insert(p6);
		// kieSession.insert(p7);
		kieSession.insert(dis);*/

		int broj = kieSession.fireAllRules();
		System.out.println("BROJ POKRENUTIH PRAVILA: " + broj);
		System.out.println(kieSession.getFactCount());
		kieSession.dispose();
		
		for (Dish d : dishes) {
			dishRepo.save(d);
		}
		
		for (Price p : prices) {
			priceRepo.save(p);
		}
		
		for (Restaurant r : restaurants) {
			resRepo.save(r);
		}

		for (User u : users) {
			repository.save(u);
		}
	}

}
