package sbnz.integracija.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import sbnz.integracija.example.dto.UserDTO;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.model.UserCategory;
import sbnz.integracija.example.model.UserRole;
import sbnz.integracija.example.service.AuthService;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

	private AuthService authService;
	
	public AuthController(AuthService service) {
		this.authService = service;
	}
	
	@PostMapping(value = "register")
	public void register(@RequestBody UserDTO dto) {
		User user = new User();
		user.setName(dto.getName());
		user.setSurname(dto.getSurname());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setRole(UserRole.REGULAR_USER);
		user.setCategory(UserCategory.NONE);
		
		try {
			authService.register(user);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with provided email already exists");
		}
		
	}
	
	
	@PostMapping(value = "login")
	public String login(@RequestBody UserDTO dto) {
		try {
			return authService.login(dto.getEmail(), dto.getPassword());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User doesn't exist, or password is incorrect");
		}
		
	}
}
