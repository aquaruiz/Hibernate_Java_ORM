package entities;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue
	private int id;
	
	private String name ;
	
	private Double quantity; 
	
	private BigDecimal price;
	
	@OneToMany(targetEntity = Sale.class, mappedBy = "product")
	private Set<Sale> sales;
	
	public Product() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public Set<Sale> getSales() {
		return sales;
	}
	
	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}
}
