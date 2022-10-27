package io.github.dev_alan87.sales.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.dev_alan87.sales.api.controller.dto.PurchaseDTO;
import io.github.dev_alan87.sales.service.PurchaseService;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

	private PurchaseService service;
	
	public PurchaseController(PurchaseService service) {
		this.service = service;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Integer save(@RequestBody PurchaseDTO dto) {
		return service.save(dto).getId();
	}
	
	
}
