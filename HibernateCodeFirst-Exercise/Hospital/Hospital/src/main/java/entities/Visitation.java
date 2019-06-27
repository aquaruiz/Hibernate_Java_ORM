package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseId {
	private Date date;
	
	private String comments;
	
	@ManyToOne(
        targetEntity = Patient.class,
        cascade = CascadeType.ALL
    )
	private Patient patient;
	
	public Visitation() {
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}