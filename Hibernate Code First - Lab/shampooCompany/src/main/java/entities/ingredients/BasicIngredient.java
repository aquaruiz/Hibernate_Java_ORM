package entities.ingredients;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import entities.shampoos.BasicShampoo;

@SuppressWarnings("serial")
@Entity
@Table(name = "ingredients")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "indergrient_type", discriminatorType = DiscriminatorType.STRING)
public abstract class BasicIngredient implements Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private BigDecimal price;
	@ManyToMany(mappedBy = "ingredients", cascade =  CascadeType.ALL)
	private List<BasicShampoo> shampoos;
	
	protected BasicIngredient() {

	}

	protected BasicIngredient(String name, BigDecimal price) {
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