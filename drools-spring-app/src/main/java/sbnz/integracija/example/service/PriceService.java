package sbnz.integracija.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.Dish;
import sbnz.integracija.example.model.Price;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.repository.PriceRepository;
@Service
public class PriceService {

	private PriceRepository priceRepository;

	@Autowired
	public PriceService(PriceRepository priceRepository) {
		super();
		this.priceRepository = priceRepository;
	}
	
	public Price getPrice(Dish dish, Restaurant res) {
		return priceRepository.findByDishAndRestaurant(dish, res);
	}
}
