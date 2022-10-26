package io.github.dev_alan87.sales.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.dev_alan87.sales.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

	private PurchaseService service;
	
	public PurchaseController(PurchaseService service) {
		this.service = service;
	}
	
	
	
}
