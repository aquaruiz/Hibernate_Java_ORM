package entities.shampoos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import entities.Size;
import entities.labels.BasicLabel;

@Entity
@DiscriminatorValue(value = "FN")
public class FreshNuke extends BasicShampoo {
	private static final String BRAND = "Fresh Nuke";
	private static final BigDecimal PRICE = BigDecimal.valueOf(9.33);
	private static final Size SIZE = Size.BIG;

	public FreshNuke(BasicLabel classicLabel) {
		super(BRAND, PRICE, SIZE, classicLabel);
	}
}
