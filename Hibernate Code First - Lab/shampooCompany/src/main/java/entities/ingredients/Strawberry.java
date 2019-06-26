package entities.ingredients;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "ST")
public class Strawberry extends BasicIngredient {
	private static final String NAME = "Strawberry";
	private static final BigDecimal PRICE =  BigDecimal.valueOf(4.85);
	
	public Strawberry() {
		super(NAME, PRICE);
	}
}
