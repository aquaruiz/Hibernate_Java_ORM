package entities.shampoos;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import entities.Size;
import entities.ingredients.BasicIngredient;
import entities.labels.BasicLabel;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public class BasicShampoo implements Shampoo {
	@Id
	@GeneratedValue
	private long id;
	private BigDecimal price;
	private String brand;
	
	@Enumerated
	private Size size;
	
	@OneToOne(
			optional = true,
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
		)
	private BasicLabel label;
	
	@ManyToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
		)
	@JoinTable(
			name = "shampoos_ingredients",
			joinColumns = @JoinColumn(
						name = "shampoo_id",
						referencedColumnName = "id"
					),
			inverseJoinColumns = @JoinColumn(
						name = "ingredient_id",
						referencedColumnName = "id"
					)
			)
	Set<BasicIngredient> ingredients;
	
	protected BasicShampoo() {
		this.ingredients = new HashSet<>();
	}
	
	public BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel label) {
		this();
		this.price = price;
		this.brand = brand;
		this.size = size;
		this.label = label;
	}

	public long getId() {
		return id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public Size getSize() {
		return size;
	}
	
	public Set<BasicIngredient> getIngredients() {
		return ingredients;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setSize(Size size) {
		this.size = size;
	}
	
	public void setIngredients(Set<BasicIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	public BasicLabel getLabel() {
		return label;
	}

	public void setLabel(BasicLabel label) {
		this.label = label;
	}
}