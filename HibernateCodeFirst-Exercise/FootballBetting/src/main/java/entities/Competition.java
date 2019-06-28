package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "competitions")
public class Competition extends BaseName {
	private CompetitionType type;
	
	public Competition() {
	}

	public CompetitionType getType() {
		return type;
	}
	
	public void setType(CompetitionType type) {
		this.type = type;
	}
}