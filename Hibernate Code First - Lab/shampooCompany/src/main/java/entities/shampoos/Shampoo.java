package entities.shampoos;

import java.math.BigDecimal;
import java.util.Set;

import entities.Size;
import entities.ingredients.BasicIngredient;
import entities.labels.BasicLabel;

public interface Shampoo {
	long getId();
	
	void setId();
	
	String getBrand();
	
	void setBrand(String brand);
	
	BigDecimal getPrice();
	
	void setPrice(BigDecimal price);
	
	Size getSize();
	
	void setSize(Size size);
	
	BasicLabel getLabel();
	
	void setLabel(BasicLabel label);
	
	Set<BasicIngredient> getIngredients();
	
	void setBasicIngredients(Set<BasicIngredient> ingredients);
}
