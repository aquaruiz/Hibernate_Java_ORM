package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "competition_types")
public class CompetitionType extends BaseId {
	private Type name;
	
	public CompetitionType() {
	}

	public Type getName() {
		return name;
	}
	
	public void setName(Type name) {
		this.name = name;
	}
}
