package sbnz.integracija.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sbnz.integracija.example.dto.DishDTO;
import sbnz.integracija.example.dto.RestaurantDTO;
import sbnz.integracija.example.model.CuisineType;
import sbnz.integracija.example.model.Delivery;
import sbnz.integracija.example.model.Dish;
import sbnz.integracija.example.model.DishType;
import sbnz.integracija.example.model.FoodType;
import sbnz.integracija.example.model.Price;
import sbnz.integracija.example.model.PriceCategory;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.service.PriceService;
import sbnz.integracija.example.service.RestaurantService;
import sbnz.integracija.example.util.JwtUtil;

@RestController
@RequestMapping(value = "restaurants")
public class RestaurantController {

	private RestaurantService resService;
	private PriceService priceService;
	private JwtUtil jwtUtil;

	public RestaurantController(RestaurantService resService, PriceService priceService) {
		this.resService = resService;
		this.priceService = priceService;
	}

	@GetMapping(value = "all")
	public List<RestaurantDTO> getAll() {
		List<RestaurantDTO> result = new ArrayList<RestaurantDTO>();
		for (Restaurant r : resService.getAllRestaurants()) {
			RestaurantDTO dto = new RestaurantDTO();
			dto.setCuisine(r.getCusine().toString());
			dto.setDelivery(r.getDelivery().toString());
			dto.setName(r.getName());
			result.add(dto);
		}
		return result;

	}

	@GetMapping(value = "foodTypes")
	public List<String> getFoodTypes() {
		List<String> result = new ArrayList<String>();
		FoodType[] allTypes = FoodType.COOKED_MEALS.getDeclaringClass().getEnumConstants();
		for (FoodType t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}

	@GetMapping(value = "dishTypes")
	public List<String> getDishTypes() {
		List<String> result = new ArrayList<String>();
		DishType[] allTypes = DishType.APPETIZER.getDeclaringClass().getEnumConstants();
		for (DishType t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}

	@GetMapping(value = "priceTypes")
	public List<String> getPriceTypes() {
		List<String> result = new ArrayList<String>();
		PriceCategory[] allTypes = PriceCategory.CHEAP.getDeclaringClass().getEnumConstants();
		for (PriceCategory t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}

	@GetMapping(value = "cuisineTypes")
	public List<String> getCuisineTypes() {
		List<String> result = new ArrayList<String>();
		CuisineType[] allTypes = CuisineType.CHINESE.getDeclaringClass().getEnumConstants();
		for (CuisineType t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}

	@GetMapping(value = "deliveryTypes")
	public List<String> getDeliveryTypes() {
		List<String> result = new ArrayList<String>();
		Delivery[] allTypes = Delivery.GLOVO.getDeclaringClass().getEnumConstants();
		for (Delivery t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}

	@GetMapping(value = "getOne/{name}")
	public RestaurantDTO getRestaurantByName(@PathVariable("name") String name) {
		RestaurantDTO dto = new RestaurantDTO();
		Restaurant res = resService.getRestaurantByName(name);
		dto.setName(name);
		dto.setCuisine(res.getCusine().toString());
		dto.setDelivery(res.getDelivery().toString());
		List<DishDTO> dishes = new ArrayList<DishDTO>();
		for (Dish d : res.getMenu()) {
			Price price = priceService.getPrice(d, res);
			DishDTO dishDto = new DishDTO(d.getId(),d.getName(), price.getPrice(), d.getDishType().toString(),
					d.getFoodType().toString());
			dishes.add(dishDto);
		}
		dto.setMenu(dishes);
		return dto;
	}
	
	@PostMapping(value = "calculatePrice/{name}")
	public String calculateTotalPrice(HttpServletRequest request,@PathVariable("name") String name, @RequestBody List<DishDTO> dtos) {
		//String jwtToken = jwtUtil.getJwtFromRequest(request);
		
			double price  = resService.calculateTotalPrice(name,dtos);
			return String.valueOf(price);
		
		
	}
}
