package com.aravind.Springboot;

import com.aravind.Springboot.customer.CustomerController;
import com.aravind.Springboot.customer.CustomerDataServiceAccess;
import com.aravind.Springboot.customer.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		/*
		Don't do this.
		CustomerService customerService =
				new CustomerService(new CustomerDataServiceAccess());
		CustomerController customerController =
				new CustomerController(customerService);*/
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootApplication.class, args);
	   String[] beanDefinitionName = applicationContext.getBeanDefinitionNames();
		for (String s : beanDefinitionName) {
			System.out.println(s);
		}
	}
	}


//	@GetMapping("/greet")
//	public Greetresponse greet(){
//		return new Greetresponse("Hello");
//	}
//
//	record Greetresponse (String greet){}


