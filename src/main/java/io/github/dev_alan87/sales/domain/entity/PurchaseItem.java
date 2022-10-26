package io.github.dev_alan87.sales.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "purchase_item")
@Getter @Setter
public class PurchaseItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "purchase_id")
	private Purchase purchase;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Column(nullable = false)
	private Integer qty;
	
}
