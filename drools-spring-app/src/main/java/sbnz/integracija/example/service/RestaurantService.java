package sbnz.integracija.example.service;

import java.util.ArrayList;
import java.util.List;


import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.dto.DishDTO;
import sbnz.integracija.example.dto.RestaurantDTO;
import sbnz.integracija.example.model.Dish;
import sbnz.integracija.example.model.Order;
import sbnz.integracija.example.model.Price;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.model.UserCategory;
import sbnz.integracija.example.model.UserRole;
import sbnz.integracija.example.repository.DishRepository;
import sbnz.integracija.example.repository.OrderRepository;
import sbnz.integracija.example.repository.PriceRepository;
import sbnz.integracija.example.repository.RestaurantRepository;
import sbnz.integracija.example.repository.UserRepository;

@Service
public class RestaurantService {

	private RestaurantRepository restaurantRepository;
	private final KieContainer kieContainer;
	private AuthService authService;
	private OrderRepository orderRepo;
	private PriceRepository priceRepo;
	private DishRepository dishRepo;
	private UserRepository userRepo;

	@Autowired
	public RestaurantService(RestaurantRepository repo, KieContainer kieContainer, AuthService authService,
			OrderRepository orderRepo, PriceRepository priceRepo,DishRepository dishRepo, UserRepository userRepo) {
		this.restaurantRepository = repo;
		this.authService = authService;
		this.kieContainer = kieContainer;
		this.orderRepo = orderRepo;
		this.priceRepo = priceRepo;
		this.dishRepo = dishRepo;
		this.userRepo = userRepo;
	}

	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	public List<RestaurantDTO> searchRestaurants() {
		return null;
	}

	public Restaurant getRestaurantByName(String name) {
		return restaurantRepository.findByName(name);
	}

	public String calculateTotalPrice(String name, List<DishDTO> dtos,User u) {
		Restaurant r = getRestaurantByName(name);	
		Order o = new Order();
		o.setUser(u);
		o.setRestaurant(r);
		
		List<Dish> dishes = new ArrayList<Dish>();
		
		for(DishDTO dto : dtos) {
			Dish d = dishRepo.findById(dto.getId()).get();
			dishes.add(d);
			
		}
		o.setDishes(dishes);
		
		KieSession kieSession  = kieContainer.newKieSession();
		kieSession.getAgenda().getAgendaGroup("total-price").setFocus();
		
		authService.insertaDataFromDataBase(kieSession);
		orderRepo.save(o);

		for(Order or : orderRepo.findAll()) {
			kieSession.insert(or);
		}
		
		kieSession.fireAllRules();
		kieSession.dispose();
		
		double totalPrice = o.getTotalPrice();
		
		return  u.getCategory().toString() + "-" + String.valueOf(totalPrice);

	}
}
