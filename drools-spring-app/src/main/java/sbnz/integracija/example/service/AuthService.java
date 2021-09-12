package sbnz.integracija.example.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Discount;
import sbnz.integracija.example.model.Dish;
import sbnz.integracija.example.model.Price;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.model.UserRole;
import sbnz.integracija.example.repository.DiscountRepository;
import sbnz.integracija.example.repository.DishRepository;
import sbnz.integracija.example.repository.PriceRepository;
import sbnz.integracija.example.repository.RestaurantRepository;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.util.JwtUtil;
import sbnz.integracija.example.util.UserClaims;

@Service
public class AuthService {

	private JwtUtil jwtUtil;
	private UserRepository userRepo;
	private PriceRepository priceRepo;
	private RestaurantRepository resRepo;
	private DishRepository dishRepo;
	private final KieContainer kieContainer;
	private DiscountRepository discRepo;

	@Autowired
	public AuthService(JwtUtil jwtUtil, UserRepository repo, KieContainer kieContainer,PriceRepository priceRepo,
			DishRepository dishRepo, RestaurantRepository resRepo, DiscountRepository discountRepository) {
		this.jwtUtil = jwtUtil;
		this.userRepo = repo;
		this.kieContainer = kieContainer;
		this.priceRepo = priceRepo;
		this.dishRepo = dishRepo;
		this.resRepo = resRepo;
		this.discRepo = discountRepository;
	}

	public boolean register(User user) throws Exception {
		if (userRepo.findByEmail(user.getEmail()) == null) {
			userRepo.save(user);
			KieSession kieSession = kieContainer.newKieSession();
			kieSession.insert(user);
			insertaDataFromDataBase(kieSession);
			kieSession.fireAllRules();
			kieSession.dispose();
			return true;
		} else {
			throw new Exception();
		}
	}

	public String login(String email, String password) throws Exception {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				UserClaims claims = new UserClaims();
				claims.setClaimValue("name", user.getName());
				claims.setClaimValue("surname", user.getSurname());
				claims.setClaimValue("email", user.getEmail());
				claims.setClaimValue("role", user.getRole().toString());
				return jwtUtil.generateJwt(claims);
			}
		}
		throw new Exception();
	}

	public User getLogedInUser(String jwtToken) throws Exception {
		if (!jwtUtil.expired(jwtToken)) {
			
			Set<String> claimsFromJwt = new HashSet<String>();
			claimsFromJwt.add("email");
			UserClaims claims = jwtUtil.decodeJwt(jwtToken, claimsFromJwt);
			User user = userRepo.findByEmail(claims.getClaimValue("email"));
			
			if (user != null) {
				return user;
			} else {
				throw new Exception();
			}
		} else
			throw new Exception();

	}
	
	public User checkUserRole(String jwtToken, UserRole role) throws Exception {
		User user = getLogedInUser(jwtToken);
		if(user.getRole().equals(role)) {
			return user;
		}else {
			throw new Exception();
		}
	}
	
	public void insertaDataFromDataBase(KieSession kieSession) {
		List<User> users = userRepo.findAll();
		List<Restaurant> restaurants =  resRepo.findAll();
		List<Price> prices = priceRepo.findAll();
		List<Dish> dishes = dishRepo.findAll();
		List<Discount> discounts = discRepo.findAll();
		
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
		for(Discount d : discounts) {
			kieSession.insert(d);
		}
	}
	private void saveToDb() {
		List<User> users = userRepo.findAll();
		List<Restaurant> restaurants =  resRepo.findAll();
		List<Price> prices = priceRepo.findAll();
		List<Dish> dishes = dishRepo.findAll();
		
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
			userRepo.save(u);
		}
	}
}
