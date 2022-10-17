package io.github.dev_alan87.sales.domain.respository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.dev_alan87.sales.domain.entity.Client;

@Repository
public class Clients {

	@Autowired
	private EntityManager manager;
	
	@Transactional
	public Client save(Client client) {
		manager.persist(client);
		return client;
	}
	
	@Transactional
	public Client update(Client client) {
		manager.merge(client);
		return client;
	}
	
	@Transactional
	public void delete(Client client) {
		manager.remove(client);
	}
	@Transactional
	public void delete(Integer id) {
		delete(manager.find(Client.class, id));
	}
	
	
}
