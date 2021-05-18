package sbnz.integracija.example;



import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbnz.integracija.example.model.Dish;
import sbnz.integracija.example.model.Order;
import sbnz.integracija.example.model.Price;
import sbnz.integracija.example.model.Restaurant;
import sbnz.integracija.example.model.User;
import sbnz.integracija.example.repository.UserRepository;

@RestController
public class SampleAppController {
	private static Logger log = LoggerFactory.getLogger(SampleAppController.class);

	private final KieContainer kieContainer;
	private UserRepository repository;


	@Autowired
	public SampleAppController(UserRepository repo, KieContainer kieContainer) {
		log.info("Initialising a new session.");
		this.repository = repo;
		this.kieContainer = kieContainer;
	}

	@RequestMapping(value = "/item", method = RequestMethod.GET)
	public void getUser() {

		KieSession kieSession = kieContainer.newKieSession();
		Order o1 = new Order();
		Order o2 = new Order();
		Order o3 = new Order();
		
		List<User> users = repository.findAll();
		
		for(User u : users) {
			kieSession.insert(u);
			o1.setUser(u);
			o2.setUser(u);
			o3.setUser(u);
			
		}
		
		kieSession.insert(o1);
		kieSession.insert(o2);
		kieSession.insert(o3);
		
		Dish d1 = new Dish();
		Dish d2 = new Dish();
		Dish d3 = new Dish();
		
		Restaurant r1  =new Restaurant();
		Restaurant r2  =new Restaurant();
		Restaurant r3  =new Restaurant();
		Restaurant r4  =new Restaurant();
		Restaurant r5  =new Restaurant();
		
		d1.setId(0);
		d2.setId(1);
		d3.setId(2);
		
		r1.setId(0);
		r2.setId(1);
		r3.setId(2);
		r4.setId(3);
		r5.setId(4);
		
		Price p1 = new Price();
		Price p2 = new Price();
		Price p3 = new Price();
		Price p4 = new Price();
		Price p5 = new Price();
		
		p1.setDish(d3);
		p1.setRestaurant(r1);
		p1.setPrice(524);
		
		p2.setDish(d3);
		p2.setRestaurant(r5);
		p2.setPrice(400);
		
		
		p3.setDish(d3);
		p3.setRestaurant(r2);
		p3.setPrice(600);
		
		kieSession.insert(d3);
		kieSession.insert(r1);
		kieSession.insert(r2);
		kieSession.insert(r5);
		kieSession.insert(p1);
		kieSession.insert(p2);
		kieSession.insert(p3);
		

		int broj = kieSession.fireAllRules();
		System.out.println("BROJ POKRENUTIH PRAVILA: "+ broj);
		System.out.println(kieSession.getFactCount());  
		kieSession.dispose();
	}
	
	
	
}
