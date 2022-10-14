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
	
	private static String INSERT  = "INSERT INTO CLIENT (name) VALUES (?)";
	private static String UPDATE  = "UPDATE CLIENT SET name = ? WHERE id = ?";
	private static String DELETE  = "DELETE FROM CLIENT WHERE id = ?";
	private static String LIST    = "SELECT * FROM CLIENT";
	private static String BY_NAME = " WHERE name LIKE ?";
	
	
	@Autowired
	private JdbcTemplate template;
	
	public Client save(Client client) {
		template.update(INSERT, new Object[]{client.getName()});
		return client;
	}
	
	public Client update(Client client) {
		template.update(UPDATE, new Object[] {client.getName(), client.getId()});
		return client;
	}
	
	public void delete(Client client) {
		delete(client.getId());
	}
	public void delete(Integer id) {
		template.update(DELETE, new Object[] {id});
	}
	
	public List<Client> listAll() {
		return template.query(LIST, getClientsMapper());
	}
	@SuppressWarnings("deprecation")
	public List<Client> findByName(String name) {
		return template.query(LIST + BY_NAME, new Object[]{"%"+name+"%"},getClientsMapper());
	}

	private RowMapper<Client> getClientsMapper() {
		return new RowMapper<Client>() {
			public Client mapRow(ResultSet resultSet, int i) throws SQLException {
				return new Client(resultSet.getInt("id"), resultSet.getString("name"));
			}
		};
	}
	
}
