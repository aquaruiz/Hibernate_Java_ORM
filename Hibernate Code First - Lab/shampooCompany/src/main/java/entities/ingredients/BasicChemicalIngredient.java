package entities.ingredients;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {
	private String chemicalFormula;

	public BasicChemicalIngredient() {

	}
	
	public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
		super(name, price);
		this.chemicalFormula = chemicalFormula;
	}



	public String getChemicalFormula() {
		return chemicalFormula;
	}

	public void setChemicalFormula(String chemicalFormula) {
		this.chemicalFormula = chemicalFormula;
	}
}
