package sbnz.integracija.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
