package entities.ingredients;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "AM")
public class AmmoniumChloride extends BasicChemicalIngredient {
	private static final String NAME = "AmmoniumChloride";
	private static final BigDecimal PRICE = BigDecimal.valueOf(0.59);
	private static final String FORMULA = "NH4Cl";

	public AmmoniumChloride() {
		super(NAME, PRICE, FORMULA);
	}
}
