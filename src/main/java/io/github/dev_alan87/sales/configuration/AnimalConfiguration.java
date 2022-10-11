package io.github.dev_alan87.sales.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.dev_alan87.sales.model.Animal;

@Configuration
public class AnimalConfiguration {

	@Bean(name = "dog")
	public Animal dog() {
		return new Animal() {
			@Override
			public void makeNoise() {
				System.out.println("Woof woof");
			}
		};
	}
	
	@Bean(name="cat")
	public Animal cat() {
		return new Animal() {
			@Override
			public void makeNoise() {
				System.out.println("Meowth");
			}
		};
	}
	
}
