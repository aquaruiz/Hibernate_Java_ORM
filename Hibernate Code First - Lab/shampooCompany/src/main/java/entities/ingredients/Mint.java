package entities.ingredients;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "MT")
public class Mint extends BasicIngredient {
	private static final String NAME = "Mint";
	private static final BigDecimal PRICE =  BigDecimal.valueOf(3.54);

	public Mint() {
		super(NAME, PRICE);
	}
}
