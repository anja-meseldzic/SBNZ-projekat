package sbnz.integracija.example.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.dto.RestaurantDTO;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.repository.RestaurantRepository;

@Service
public class RestaurantService {

	private RestaurantRepository restaurantRepository;
	
	@Autowired
	public RestaurantService(RestaurantRepository repo) {
		this.restaurantRepository  = repo;
	}
	
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}
	
	public List<RestaurantDTO> searchRestaurants(){
		return null;
	}
}
