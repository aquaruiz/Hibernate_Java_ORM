package entities.ingredients;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "NE")
public class Nettle extends BasicIngredient {
	private static final String NAME = "Nettle";
	private static final BigDecimal PRICE =  BigDecimal.valueOf(6.12);
	
	public Nettle() {
		super(NAME, PRICE);
	}

}
