package sbnz.integracija.example.repository;

import org.springframework.stereotype.Repository;

import sbnz.integracija.example.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
		User findById(long id);
		User findByEmail(String email);
}
