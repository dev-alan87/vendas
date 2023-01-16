package io.github.dev_alan87.sales.domain.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dev_alan87.sales.domain.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Integer> {

    Optional<MyUser> findByUsername(String username);
    
}
