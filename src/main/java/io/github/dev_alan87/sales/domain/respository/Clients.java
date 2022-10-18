package io.github.dev_alan87.sales.domain.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import io.github.dev_alan87.sales.domain.entity.Client;

public interface Clients extends JpaRepository<Client, Integer> {

	List<Client> findByNameLike(String name);
	
	@Query(value = "SELECT * FROM Client c WHERE c.name LIKE :name ", nativeQuery = true)
	List<Client> findByNameNative(String name);
	
	boolean existsByName(String name);
	
	boolean existsByNameLike(String name);
	
	@Modifying @Transactional
	Integer deleteByName(String name);
	
	@Query( "select c from Client c left join fetch c.purchases where c.id = :id" )
	Client findClientFetchPurchases(@Param("id") Integer id);
}
