package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BillingDetail extends BaseId {
	private Long number;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	private User owner;
	
	public BillingDetail() {
	}
	
	public Long getNumber() {
		return number;
	}
	
	public void setNumber(Long number) {
		this.number = number;
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
}