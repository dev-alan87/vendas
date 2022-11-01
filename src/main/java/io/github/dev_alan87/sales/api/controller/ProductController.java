package io.github.dev_alan87.sales.api.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
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

	private Products respository;
	
	public ProductController(Products products) {
		this.respository = products;
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Product saveProduct(@RequestBody Product product) {
		return respository.save(product);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
		return respository.findById(id).map(p -> {
			product.setId(p.getId());
			respository.save(product);
			return product;
		}).orElseThrow(() -> 
			new ResponseStatusException(
					HttpStatus.NOT_FOUND, 
					"Product not found."
		));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Integer id) {
		respository.findById(id).map( p -> {
			respository.delete(p);
			return p;
		}).orElseThrow(() -> 
			new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Product not found."
		));
	}
	
	@GetMapping(value = {"/{id}"})
	@ResponseStatus(value = HttpStatus.FOUND)
	public Product getProductById(@PathVariable("id") Integer id) {
		return respository.findById(id)
				.orElseThrow(() -> 
					new ResponseStatusException(
							HttpStatus.NOT_FOUND, 
							"Product not found."
				));
	}
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.FOUND)
	public List<Product> findProduct(Product filter) {
		ExampleMatcher matcher = ExampleMatcher.matching().
				withIgnoreCase().
				withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Product> example = Example.of(filter, matcher);
		return respository.findAll(example);
	}
}
