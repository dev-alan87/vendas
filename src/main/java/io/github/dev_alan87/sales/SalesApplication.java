package io.github.dev_alan87.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import io.github.dev_alan87.sales.domain.entity.Client;
import io.github.dev_alan87.sales.domain.respository.Clients;

@SpringBootApplication
@RestController
public class SalesApplication {

	@Bean
	public CommandLineRunner init(@Autowired Clients clients) {
		return args -> {
			System.out.println("Saving clients...");
			clients.save(new Client("Alan Alves"));
			clients.save(new Client("Maria do Carmo"));
			
			System.out.println("\nListing clients...");
			List<Client> listClients = clients.listAll();
			listClients.forEach(System.out::println);
			
			System.out.println("\nUpdating clients...");
			listClients.forEach(client -> {
				client.setName(client.getName() + " (updated)");
				clients.update(client);
			});
			
			System.out.println("\nListing clients...");
			listClients = clients.listAll();
			listClients.forEach(System.out::println);
			
			System.out.println("\nFind clients...");
			clients.findByName("Carmo").forEach(System.out::println);
			
			System.out.println("\nDeleting clients...");
			clients.listAll().forEach(client -> {
				clients.delete(client);
			});
			listClients = clients.listAll();
			if(listClients.isEmpty()) {
				System.out.println("There aren't clients registered.");
			} else {
				listClients.forEach(System.out::println);
			}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SalesApplication.class, args);
	}
	
}
