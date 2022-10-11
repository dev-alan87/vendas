package io.github.dev_alan87.sales.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan(basePackages = {
		"io.github.dev_alan87.sales.configuration", 
		"io.github.dev_alan87.sales.respository", 
		"io.github.dev_alan87.sales.service"
})
@RestController
public class SalesApplication {
	
	@Value("${application.name}")
	private String applicationName;
	
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