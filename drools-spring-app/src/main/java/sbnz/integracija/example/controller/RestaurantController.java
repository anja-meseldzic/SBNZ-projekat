package sbnz.integracija.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.dto.RestaurantDTO;
import sbnz.integracija.example.model.CuisineType;
import sbnz.integracija.example.model.Delivery;
import sbnz.integracija.example.model.DishType;
import sbnz.integracija.example.model.FoodType;
import sbnz.integracija.example.model.PriceCategory;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.service.RestaurantService;

@RestController
@RequestMapping(value = "restaurants")
public class RestaurantController {
	
	private RestaurantService resService;
	
	public RestaurantController(RestaurantService resService) {
		this.resService = resService;
	}
	
	@GetMapping(value = "all")
	public List<RestaurantDTO> getAll(){
		List<RestaurantDTO> result = new ArrayList<RestaurantDTO>();
		for(Restaurant r : resService.getAllRestaurants()) {
			RestaurantDTO dto = new RestaurantDTO();
			dto.setCuisine(r.getCusine().toString());
			dto.setDelivery(r.getDelivery().toString());
			dto.setName(r.getName());
			result.add(dto);
		}
		return result;
		
	}
	
	@GetMapping(value = "foodTypes")
	public List<String> getFoodTypes(){
		List<String> result = new ArrayList<String>();
		FoodType[] allTypes = FoodType.COOKED_MEALS.getDeclaringClass().getEnumConstants();
		for(FoodType t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}
	
	@GetMapping(value = "dishTypes")
	public List<String> getDishTypes(){
		List<String> result = new ArrayList<String>();
		DishType[] allTypes = DishType.APPETIZER.getDeclaringClass().getEnumConstants();
		for(DishType t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}
	
	@GetMapping(value = "priceTypes")
	public List<String> getPriceTypes(){
		List<String> result = new ArrayList<String>();
		PriceCategory[] allTypes = PriceCategory.CHEAP.getDeclaringClass().getEnumConstants();
		for(PriceCategory t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}
	
	@GetMapping(value = "cuisineTypes")
	public List<String> getCuisineTypes(){
		List<String> result = new ArrayList<String>();
		CuisineType[] allTypes = CuisineType.CHINESE.getDeclaringClass().getEnumConstants();
		for(CuisineType t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}
	
	@GetMapping(value = "deliveryTypes")
	public List<String> getDeliveryTypes(){
		List<String> result = new ArrayList<String>();
		Delivery[] allTypes = Delivery.GLOVO.getDeclaringClass().getEnumConstants();
		for(Delivery t : allTypes) {
			result.add(t.toString());
		}
		return result;
	}
}
