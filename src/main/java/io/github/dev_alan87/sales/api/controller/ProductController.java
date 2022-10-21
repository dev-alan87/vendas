package io.github.dev_alan87.sales.api.controller;

import java.util.List;

import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.dev_alan87.sales.domain.entity.Product;
import io.github.dev_alan87.sales.domain.respository.Products;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	
	private Products products;
	
	public ProductController(Products products) {
		this.products = products;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Product saveProduct(Product product) {
		return products.save(product);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		return products.findById(id)
				.map(p -> {
					product.setId(id);
					products.save(product);
					return product;
				}).orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.NOT_FOUND, 
								"Product not found."));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Integer id) {
		products.findById(id)
			.map(p -> {
				products.delete(p);
				return p;
			}).orElseThrow(() -> 
					new ResponseStatusException(
							HttpStatus.NOT_FOUND,
							"Product not found."));
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(value = HttpStatus.FOUND)
	public Product getProductById(@PathVariable Integer id) {
		return products.findById(id)
				.orElseThrow(() -> 
				new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Product not found."));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.FOUND)
	public List<Product> findProduct() {
		//TODO: continue from here
		ExampleMatcher matcher;
		
		return null;
	}
}
