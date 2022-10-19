package io.github.dev_alan87.sales;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import io.github.dev_alan87.sales.domain.entity.Client;
import io.github.dev_alan87.sales.domain.entity.Purchase;
import io.github.dev_alan87.sales.domain.respository.Clients;
import io.github.dev_alan87.sales.domain.respository.Purchases;

@SpringBootApplication
@RestController
public class SalesApplication {

	@Bean
	public CommandLineRunner init(
			@Autowired Clients clients,
			@Autowired Purchases purchases
			) {
		return args -> {
			
			Client clientAlan = new Client("Alan Alves");
			clients.save(clientAlan);
			
			Purchase purchase01 = new Purchase();
			purchase01.setClient(clientAlan);
			purchase01.setPurchaseDate(LocalDate.now());
			purchase01.setTotal(BigDecimal.valueOf(100));
			purchases.save(purchase01);
			
//			Client client = clients.findClientFetchPurchases(clientAlan.getId());
//			System.out.println(client);
//			System.out.println(client.getPurchases());
			
			purchases.findByClient(clientAlan).forEach(System.out::println);
			
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
}
