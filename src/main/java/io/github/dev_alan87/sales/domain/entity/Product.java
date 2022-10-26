package io.github.dev_alan87.sales.domain.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter @Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "description", length = 1000)
	private String description;
	
	@Column(name = "unit_price", precision = 20, scale = 2)
	private BigDecimal price;
	
}
