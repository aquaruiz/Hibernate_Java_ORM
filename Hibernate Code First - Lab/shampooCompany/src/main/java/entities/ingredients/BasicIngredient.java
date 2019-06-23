package entities.ingredients;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import entities.shampoos.BasicShampoo;

@Entity
@SuppressWarnings("serial")
public abstract class BasicIngredient implements Ingredient {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private BigDecimal price;
	private List<BasicShampoo> shampoos;
	
	public BasicIngredient() {

	}

	public BasicIngredient(String name, BigDecimal price) {
		setName(name);
		setPrice(price);
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
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<BasicShampoo> getShampoos() {
		return shampoos;
	}
	
	public void setShampoos(List<BasicShampoo> shampoos) {
		this.shampoos = shampoos;
	}
}