package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.Dish;
@Repository
public interface DishRepository extends JpaRepository<Dish, Long>{

}
