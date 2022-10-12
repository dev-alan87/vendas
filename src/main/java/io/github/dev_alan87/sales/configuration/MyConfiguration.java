package io.github.dev_alan87.sales.configuration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MyConfiguration {

	@Bean
	public CommandLineRunner execute() {
		return args -> {
			System.out.println("RUNNING A DEVELOPMENT CONFIGURATION...");
		};
	}
	
}