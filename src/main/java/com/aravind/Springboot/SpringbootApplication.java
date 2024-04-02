package com.aravind.Springboot;

import com.aravind.Springboot.customer.Customer;
import com.aravind.Springboot.customer.CustomerRepository;
import com.aravind.Springboot.customer.Gender;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

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

		SpringApplication.run(SpringbootApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(CustomerRepository customerRepository) {
		return args -> {
			var faker = new Faker();
			Random random = new Random();
			Name name = faker.name();
			String firstName = name.firstName();
			String lastName = name.lastName();
			int age = random.nextInt(16,99);
			Gender gender = age % 2 == 0 ? Gender.MALE:Gender.MALE;
			Customer customer1 = new Customer(
				firstName+" "+lastName,
					firstName.toLowerCase()+"."+lastName.toLowerCase()+"@gmail.com",
					age,
					gender);


			customerRepository.save(customer1);
		};


	}
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


