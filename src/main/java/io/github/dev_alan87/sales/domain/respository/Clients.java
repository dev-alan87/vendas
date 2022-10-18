package io.github.dev_alan87.sales.domain.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dev_alan87.sales.domain.entity.Client;

public interface Clients extends JpaRepository<Client, Integer> {

	List<Client> findByNameLike(String name);
	boolean existsByName(String name);
	boolean existsByNameLike(String name);
	
}
