package io.github.dev_alan87.sales.service.impl;

import org.springframework.stereotype.Service;

import io.github.dev_alan87.sales.domain.respository.Purchases;
import io.github.dev_alan87.sales.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private Purchases repository;
	
	public PurchaseServiceImpl(Purchases repository) {
		this.repository = repository;
	}
	
	
	
}
