package io.github.dev_alan87.sales.domain.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		if(!manager.contains(client))
			client = manager.merge(client);
		
		manager.remove(client);
	}
	@Transactional
	public void delete(Integer id) {
		delete(manager.find(Client.class, id));
	}
	
	@Transactional(readOnly = true)
	public List<Client> findByName(String name) {
		String jpql = "select c from Client c where c.name like :name";
		TypedQuery<Client> query = manager.createQuery(jpql, Client.class);
		query.setParameter("name", "%"+name+"%");
		
		return query.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Client> listAll() {
		return manager.createQuery("from Client", Client.class).getResultList();
	}
	
}
