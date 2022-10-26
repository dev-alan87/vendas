package io.github.dev_alan87.sales.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PURCHASE")
@Getter @Setter
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
	
	@OneToMany(mappedBy = "purchase")
	private List<PurchaseItem> items;
	
	@Override
	public String toString() {
		return "Purchase [id=" + id + ", purchaseDate=" + purchaseDate + ", total=" + total + "]";
	}
	
}
