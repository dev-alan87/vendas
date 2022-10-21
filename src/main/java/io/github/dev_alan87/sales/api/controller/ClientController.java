package io.github.dev_alan87.sales.api.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dev_alan87.sales.domain.entity.Client;
import io.github.dev_alan87.sales.domain.respository.Clients;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

	private Clients clients;
	
	public ClientController(Clients clients) {
		this.clients = clients;
	}
	
	@RequestMapping(
				value = { "/hello/{name}", "/welcome" }, 
				method = RequestMethod.POST,
				consumes = { "application/json", "application/xml" },
				produces = { "application/json", "application/xml" }
			)
	public String hellloClient(@PathVariable("name") String clientName, @RequestBody Client client) {
		return String.format("Hello, %s. Welcome!", clientName);
	}
	
	@GetMapping(value = {"/{id}"})
	@ResponseStatus(value = HttpStatus.FOUND)
	public Client getClientById(@PathVariable("id") Integer id) {
		return clients.findById(id)
				.orElseThrow(() -> 
					new ResponseStatusException(
							HttpStatus.NOT_FOUND, 
							"Client not found."
				));
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Client saveClient(@RequestBody Client client) {
		return clients.save(client);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteClient(@PathVariable Integer id) {
		clients.findById(id).map( c -> {
			clients.delete(c);
			return c;
		}).orElseThrow(() -> 
			new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Client not found."
		));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
		return clients.findById(id).map(c -> {
			client.setId(c.getId());
			clients.save(client);
			return client;
		}).orElseThrow(() -> 
			new ResponseStatusException(
					HttpStatus.NOT_FOUND, 
					"Client not found."
		));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.FOUND)
	public List<Client> findClient(Client filter) {
		ExampleMatcher matcher = ExampleMatcher.matching().
				withIgnoreCase().
				withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Client> example = Example.of(filter, matcher);
		return clients.findAll(example);
	}
	
}