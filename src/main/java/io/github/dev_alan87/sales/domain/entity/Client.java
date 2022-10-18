package io.github.dev_alan87.sales.domain.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", length=250, nullable=false)
	private String name;
	
	@OneToMany(mappedBy = "client")
	private Set<Purchase> purchases;
	
	public Client(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Client(String name) {
		this.name = name;
	}
	public Client() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Purchase> getPurchases() {
		return purchases;
	}
	public void setPurchases(Set<Purchase> purchases) {
		this.purchases = purchases;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}
	
}
