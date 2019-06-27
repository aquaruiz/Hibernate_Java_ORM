package entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient extends BaseId {
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	private  String address;
	
	private String email;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;
	
	private String picture;

	@Column(name = "has_medical_insurance")
	private boolean hasMedicalInsurance;
	
	@ManyToMany(targetEntity = Diagnose.class, cascade = CascadeType.ALL)
	@JoinTable(name = "patients_diagnoses", 
			joinColumns = @JoinColumn (name = "patient_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn (name = "diagnose_id", referencedColumnName = "id"))
	private Set<Diagnose> diagnoses;
	
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private Set<Visitation> visitations;
	
	@ManyToMany(targetEntity = Medicament.class ,cascade = CascadeType.ALL)
	@JoinTable(name = "patients_medicaments",
			joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
	private Set<Medicament> medicaments;
	
	public Patient() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public boolean isHasMedicalInsurance() {
		return hasMedicalInsurance;
	}

	public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
		this.hasMedicalInsurance = hasMedicalInsurance;
	}

	public Set<Diagnose> getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(Set<Diagnose> diagnoses) {
		this.diagnoses = diagnoses;
	}

	public Set<Visitation> getVisitations() {
		return visitations;
	}

	public void setVisitations(Set<Visitation> visitations) {
		this.visitations = visitations;
	}

	public Set<Medicament> getMedicaments() {
		return medicaments;
	}

	public void setMedicaments(Set<Medicament> prescribedMedicaments) {
		this.medicaments = prescribedMedicaments;
	}
}