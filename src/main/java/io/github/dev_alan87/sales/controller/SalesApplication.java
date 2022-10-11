package io.github.dev_alan87.sales.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dev_alan87.sales.model.Animal;
import io.github.dev_alan87.sales.model.Dog;

@SpringBootApplication
@ComponentScan(basePackages = {
		"io.github.dev_alan87.sales.configuration", 
		"io.github.dev_alan87.sales.respository", 
		"io.github.dev_alan87.sales.service"
})
@RestController
public class SalesApplication {

	@Dog
	private Animal animal;
	
	@Value("${application.name}")
	private String applicationName;
	
	@Bean(name = "executeAnimalNoise")
	public CommandLineRunner execute() {
		return args -> {
			this.animal.makeNoise();
		};
	}
	
	@GetMapping("/hello")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping("/about")
	public String applicationName() {
		return this.applicationName;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
}