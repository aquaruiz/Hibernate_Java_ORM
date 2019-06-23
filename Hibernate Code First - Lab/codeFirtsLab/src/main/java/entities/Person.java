package entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

//@MappedSuperclass
@Entity(name = "people")
@DiscriminatorColumn(name = "pt")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(
			name = "name", 
			length = 50
	)
	private String name;
	
	@Column
	private int age;
	
	@OneToOne(mappedBy = "child",
			targetEntity = Mother.class)
	private Mother mother;
	
	public Person() {

	}
	
	public Person(String name) {
		setName(name);
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Mother getMother() {
		return mother;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMother(Mother mother) {
		this.mother = mother;
	}
}
