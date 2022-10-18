package io.github.dev_alan87.sales.domain.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.dev_alan87.sales.domain.entity.PurchaseItem;

public interface PurchaseItems extends JpaRepository<PurchaseItem, Integer> {

}
