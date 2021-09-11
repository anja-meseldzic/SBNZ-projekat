package sbnz.integracija.example.service;

import java.util.HashSet;
import java.util.Set;

import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbnz.integracija.example.model.User;
import sbnz.integracija.example.model.UserRole;
import sbnz.integracija.example.repository.UserRepository;
import sbnz.integracija.example.util.JwtUtil;
import sbnz.integracija.example.util.UserClaims;

@Service
public class AuthService {

	private JwtUtil jwtUtil;
	private UserRepository userRepo;
	private final KieContainer kieContainer;

	@Autowired
	public AuthService(JwtUtil jwtUtil, UserRepository repo, KieContainer kieContainer) {
		this.jwtUtil = jwtUtil;
		this.userRepo = repo;
		this.kieContainer = kieContainer;
	}

	public boolean register(User user) throws Exception {
		if (userRepo.findByEmail(user.getEmail()) == null) {
			userRepo.save(user);
			KieSession kieSession = kieContainer.newKieSession();
			kieSession.insert(user);
			return true;
		} else {
			throw new Exception();
		}
	}

	public String login(String email, String password) throws Exception {
		User user = userRepo.findByEmail(email);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				UserClaims claims = new UserClaims();
				claims.setClaimValue("name", user.getName());
				claims.setClaimValue("surname", user.getSurname());
				claims.setClaimValue("email", user.getEmail());
				claims.setClaimValue("role", user.getRole().toString());
				return jwtUtil.generateJwt(claims);
			}
		}
		throw new Exception();
	}

	public User getLogedInUser(String jwtToken) throws Exception {
		if (!jwtUtil.expired(jwtToken)) {
			
			Set<String> claimsFromJwt = new HashSet<String>();
			claimsFromJwt.add("email");
			UserClaims claims = jwtUtil.decodeJwt(jwtToken, claimsFromJwt);
			User user = userRepo.findByEmail(claims.getClaimValue("email"));
			
			if (user != null) {
				return user;
			} else {
				throw new Exception();
			}
		} else
			throw new Exception();

	}
	
	public User checkUserRole(String jwtToken, UserRole role) throws Exception {
		User user = getLogedInUser(jwtToken);
		if(user.getRole().equals(role)) {
			return user;
		}else {
			throw new Exception();
		}
	}
}
