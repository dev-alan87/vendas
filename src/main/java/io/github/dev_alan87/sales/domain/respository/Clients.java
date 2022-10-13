package io.github.dev_alan87.sales.domain.respository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.dev_alan87.sales.domain.entity.Client;

@Repository
public class Clients {
	
	private static String INSERT = "INSERT INTO CLIENT (name) VALUES (?)";
	private static String LIST = "SELECT * FROM CLIENT";
	
	@Autowired
	private JdbcTemplate template;
	
	public Client save(Client client) {
		template.update(INSERT, new Object[]{client.getName()});
		return client;
	}
	
	public List<Client> listAll() {
		return template.query(LIST, new RowMapper<Client>() {
			public Client mapRow(ResultSet resultSet, int i) throws SQLException {
				return new Client(resultSet.getInt("id"), resultSet.getString("name"));
			}
		});
	}
	
}
