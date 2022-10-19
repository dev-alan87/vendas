package io.github.dev_alan87.sales.domain.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dev_alan87.sales.domain.entity.Client;
import io.github.dev_alan87.sales.domain.entity.Purchase;

public interface Purchases extends JpaRepository<Purchase, Integer> {

	List<Purchase> findByClient(Client client);
	
}
