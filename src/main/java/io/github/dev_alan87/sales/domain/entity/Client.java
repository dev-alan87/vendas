package io.github.dev_alan87.sales.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="CLIENT")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name="cpf", length = 14)
	private String cpf;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Set<Purchase> purchases;
	
	public Client(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Client(String name) {
		this.name = name;
	}
}
