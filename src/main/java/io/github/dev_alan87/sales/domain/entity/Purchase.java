package io.github.dev_alan87.sales.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.github.dev_alan87.sales.domain.entity.enums.PurchaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "PURCHASE")
public class Purchase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Column(name = "purchase_date")
	private LocalDate purchaseDate;
	
	@Column(precision = 20, scale = 2)
	private BigDecimal total;

	@Enumerated(EnumType.STRING)
	private PurchaseStatus status;
	
	@OneToMany(mappedBy = "purchase")
	private List<PurchaseItem> items;
	
}
