package com.aravind.Springboot;

import com.aravind.Springboot.customer.CustomerController;
import com.aravind.Springboot.customer.CustomerDataServiceAccess;
import com.aravind.Springboot.customer.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		/*
		Don't do this.
		CustomerService customerService =
				new CustomerService(new CustomerDataServiceAccess());
		CustomerController customerController =
				new CustomerController(customerService);*/
		SpringApplication.run(SpringbootApplication.class, args);
	}
	}


//	@GetMapping("/greet")
//	public Greetresponse greet(){
//		return new Greetresponse("Hello");
//	}
//
//	record Greetresponse (String greet){}


