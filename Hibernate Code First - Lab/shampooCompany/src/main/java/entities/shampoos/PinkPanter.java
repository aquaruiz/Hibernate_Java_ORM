package entities.shampoos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import entities.Size;
import entities.labels.BasicLabel;

@Entity
@DiscriminatorValue(value = "PP")
public class PinkPanter extends BasicShampoo {
	private static final String BRAND = "Pink Panter";
	private static final BigDecimal PRICE = BigDecimal.valueOf(29.33);
	private static final Size SIZE = Size.MEDIUM;

	public PinkPanter(BasicLabel classicLabel) {
		super(BRAND, PRICE, SIZE, classicLabel);
	}
}
