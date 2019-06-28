package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "towns")
public class Town extends BaseName{
	@ManyToOne(targetEntity = Country.class, cascade = CascadeType.ALL)
	private String country;
	
	public Town() {
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}