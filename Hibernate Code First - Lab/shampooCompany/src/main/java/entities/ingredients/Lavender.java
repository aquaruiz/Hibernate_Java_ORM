package entities.ingredients;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "LV")
public class Lavender extends BasicIngredient {
	private static final String NAME = "Lavender";
	private static final BigDecimal PRICE =  BigDecimal.valueOf(2);
	
	public Lavender() {
		super(NAME, PRICE);
	}
}
