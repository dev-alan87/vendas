package io.github.dev_alan87.sales.domain.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.dev_alan87.sales.domain.entity.Client;
import io.github.dev_alan87.sales.domain.entity.Purchase;

public interface Purchases extends JpaRepository<Purchase, Integer> {

	List<Purchase> findByClient(Client client);

	@Query(value = "select p from Purchase p left join p.items where p.id = :id")
	Optional<Purchase> findByFetchItems(@Param("id") Integer id);
	
}
