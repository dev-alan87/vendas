package io.github.dev_alan87.sales.domain.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import io.github.dev_alan87.sales.domain.entity.Client;

@Repository
public class Clients {
	
	private static String INSERT = "INSERT INTO CLIENT (name) VALUES (?)";
	
	@Autowired
	private JdbcTemplate template;
	
	public Client save(Client client) {
		template.update(INSERT, new Object[]{client.getName()});
		return client;
	}
	
}
