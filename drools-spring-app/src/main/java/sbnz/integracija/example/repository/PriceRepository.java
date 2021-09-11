package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.Dish;
import sbnz.integracija.example.model.Price;
import sbnz.integracija.example.model.Restaurant;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	Price findByDishAndRestaurant(Dish dish, Restaurant restaurant);
}
