package entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "teams")
public class Team extends BaseName {
	 private String logo;
	 
	 @Column(length = 3)
	 private String initial;
	 
	 @ManyToOne(targetEntity = Color.class)
//	 @JoinColumn(name = "primary_kit_color")
	 private Color primaryKitColor;

	 @ManyToOne(targetEntity = Color.class)
//	 @JoinColumn(name = "secondary_kit_color")
	 private Color secondaryKitColor;
	 
	 @ManyToOne(targetEntity = Town.class, cascade = CascadeType.ALL)
	 private Town town;
	 
	 private double budget;
	 
	 public Team() {
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public Color getPrimaryKitColor() {
		return primaryKitColor;
	}

	public void setPrimaryKitColor(Color primaryKitColor) {
		this.primaryKitColor = primaryKitColor;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public Color getSecondaryKitColor() {
		return secondaryKitColor;
	}

	public void setSecondaryKitColor(Color secondaryKitColor) {
		this.secondaryKitColor = secondaryKitColor;
	}
	
	
}