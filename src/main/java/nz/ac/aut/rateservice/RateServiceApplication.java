package nz.ac.aut.rateservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RateServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RateServiceApplication.class, args);
	}

	@GetMapping("/")
	public String rate() {
	return String.format("Rating service!!!");
	}  

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "Web Services") String name) {
	return String.format("Hello %s!", name);
	}  
}