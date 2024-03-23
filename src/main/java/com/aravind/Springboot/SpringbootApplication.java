package com.aravind.Springboot;

import com.aravind.Springboot.customer.Customer;
import com.aravind.Springboot.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
@SpringBootApplication
public class SpringbootApplication {

	public static void main(String[] args) {
		/*
		Don't do this.
		CustomerService customerService =
				new CustomerService(new CustomerDataServiceAccess());
		CustomerController customerController =
				new CustomerController(customerService);*/

		SpringApplication.run(SpringbootApplication.class, args);}
//        @Bean
//		CommandLineRunner runner(CustomerRepository customerRepository){
//			return args ->{
//				Customer Aravind = new Customer(
//						"Aravind",
//						"aravind@gmail.com",
//						23);
//
//				Customer Aswin = new Customer(
//						"Aswin",
//						"aswin@gmail.com",
//						20);
//
//				Customer Meena = new Customer(
//						"Meena",
//						"meena@gmail.com",
//						45
//				);
//				List<Customer>customers=List.of(Aravind,Aswin,Meena);
//				customerRepository.saveAll(customers);
//			};


	}


//
//	record Foo(String name) {
//	}




//	private static	void printBeans(ConfigurableApplicationContext ctx){
//		String[] beanDefinitionName =
//				ctx.getBeanDefinitionNames();
//		for (String s : beanDefinitionName) {
//			System.out.println(s);
//		}
//	}
//
//
//
//	@GetMapping("/greet")
//	public Greetresponse greet(){
//		return new Greetresponse("Hello");
//	}
//
//	record Greetresponse (String greet){}


