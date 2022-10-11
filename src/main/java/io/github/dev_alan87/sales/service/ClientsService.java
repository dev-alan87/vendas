package io.github.dev_alan87.sales.service;

import org.springframework.stereotype.Service;

import io.github.dev_alan87.sales.model.Client;
import io.github.dev_alan87.sales.respository.ClientsRepository;

@Service
public class ClientsService {

	private ClientsRepository repository;
	
	public ClientsService(ClientsRepository repository) {
		this.repository = repository;
	}
	
	public void saveClient(Client client) {
		if(validateClient(client))
			this.repository.persist(client);
	}
	
	public boolean validateClient(Client client) {
		//TODO: implement validate clients method
		return true;
	}
	
}
