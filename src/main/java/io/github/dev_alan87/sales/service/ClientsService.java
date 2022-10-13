package io.github.dev_alan87.sales.service;

import org.springframework.stereotype.Service;

import io.github.dev_alan87.sales.domain.entity.Client;
import io.github.dev_alan87.sales.domain.respository.Clients;

@Service
public class ClientsService {

	private Clients repository;
	
	public ClientsService(Clients repository) {
		this.repository = repository;
	}
	
	public void saveClient(Client client) {
		if(validateClient(client))
			this.repository.save(client);
	}
	
	public boolean validateClient(Client client) {
		//TODO: implement validate clients method
		return true;
	}
	
}
