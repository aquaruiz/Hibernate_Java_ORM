package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "positions")
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 2)
	private int id;
	
	@Column(name = "position_description")
	private String positionDescription;
	
	public Position() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPositionDescription() {
		return positionDescription;
	}

	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}
}