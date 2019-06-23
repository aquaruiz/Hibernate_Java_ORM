package entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "mothers")
public class Mother extends Person {
	@OneToOne
	@JoinColumn(name = "child_id",
			referencedColumnName = "id")
	private Person child;
	
	public Mother() {
		// TODO Auto-generated constructor stub
	}

	public Mother(String name) {
		super(name);
	}
	
	public Person getChild() {
		return child;
	}
	
	
	public void setChild(Person child) {
		this.child = child;
	}
	
}