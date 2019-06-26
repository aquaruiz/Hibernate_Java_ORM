package entities.ingredients;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {
	@Column(name = "chemicla_formula")
	private String chemicalFormula;

	protected BasicChemicalIngredient() {

	}
	
	public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
		super(name, price);
		setChemicalFormula(chemicalFormula);
	}

	public String getChemicalFormula() {
		return chemicalFormula;
	}

	public void setChemicalFormula(String chemicalFormula) {
		this.chemicalFormula = chemicalFormula;
	}
}