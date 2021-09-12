package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long>{

}
