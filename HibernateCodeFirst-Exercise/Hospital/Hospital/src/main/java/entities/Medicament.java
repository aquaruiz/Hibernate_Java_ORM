package entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseId {
	private String name;
	
	@ManyToMany(mappedBy = "medicaments", cascade = CascadeType.ALL)
	private Set<Patient> patients;
	
	public Medicament() {
	}
																		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Set<Patient> getPatients() {
		return patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}
}