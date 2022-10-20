package io.github.dev_alan87.sales.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.dev_alan87.sales.domain.entity.Client;
import io.github.dev_alan87.sales.domain.respository.Clients;

@Controller
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
	@ResponseBody
	public String hellloClient(@PathVariable("name") String clientName, @RequestBody Client client) {
		return String.format("Hello, %s. Welcome!", clientName);
	}
	
	@GetMapping(value = {"/{id}"})
	@ResponseBody
	public ResponseEntity<Client> getClientById(@PathVariable("id") Integer id) {
		Optional<Client> client = clients.findById(id);
		if(client.isPresent())
			return ResponseEntity.ok(client.get());
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<Client> saveClient(@RequestBody Client client) {
		return ResponseEntity.ok(clients.save(client));
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Client> deleteClient(@PathVariable Integer id) {
		Optional<Client> client = clients.findById(id);
		if(client.isPresent()) {
			clients.delete(client.get());
			return ResponseEntity.noContent().build();
		} else
			return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@ResponseBody
	public ResponseEntity<Client> updateClient(@PathVariable Integer id, @RequestBody Client client) {
		return clients.findById(id).map(c -> {
			client.setId(c.getId());
			clients.save(client);
			return ResponseEntity.ok(client);
		}).orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping
	@ResponseBody
	public ResponseEntity<List> findClient(Client filter) {
		ExampleMatcher matcher = ExampleMatcher.matching().
				withIgnoreCase().
				withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Client> example = Example.of(filter, matcher);
		return ResponseEntity.ok(clients.findAll(example));
	}
	
}