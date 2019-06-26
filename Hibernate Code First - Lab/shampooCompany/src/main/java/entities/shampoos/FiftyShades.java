package entities.shampoos;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import entities.Size;
import entities.labels.BasicLabel;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShades extends BasicShampoo {
	private static final String BRAND = "Fifty Shades";
	private static final BigDecimal PRICE = BigDecimal.valueOf(0.33);
	private static final Size SIZE = Size.SMALL;

	public FiftyShades(BasicLabel classicLabel) {
		super(BRAND, PRICE, SIZE, classicLabel);
	}
}
