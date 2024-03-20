package com.aravind.Springboot;

import com.aravind.Springboot.customer.CustomerController;
import com.aravind.Springboot.customer.CustomerDataServiceAccess;
import com.aravind.Springboot.customer.CustomerService;
import com.sun.tools.javac.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
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
	public Foo getFoo() {
		return new Foo("bar");
	}

	record Foo(String name) {
	}
}


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


