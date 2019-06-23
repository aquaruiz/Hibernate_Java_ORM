package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "teachers")
@DiscriminatorValue("te")
public class Teacher extends Person{
	@Column
	private String speciality;
	
	@Column
	private boolean isSuperVisor;
	
	public Teacher() {
		
	}

	public Teacher(String name, String speciality) {
		super(name);
		setSpeciality(speciality);
	}
	
	public String getSpeciality() {
		return speciality;
	}
	
	public boolean getIsSuperVisor() {
		return this.isSuperVisor;
	}
	
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	public void setIsSuperVisor(boolean isSuperVisor) {
		this.isSuperVisor = isSuperVisor;
	}
}
