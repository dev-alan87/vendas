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
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="client")
@Data @NoArgsConstructor @AllArgsConstructor
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", length = 100, nullable = false)
	@NotEmpty(message = "{client.name.required}")
	private String name;

	@Column(name="cpf", length = 14)
	@NotEmpty(message = "{client.cpf.required}")
	@CPF(message = "{client.cpf.invalid}")
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
