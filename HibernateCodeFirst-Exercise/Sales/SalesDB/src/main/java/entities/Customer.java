package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue
    private int id;
	
    private String name;
    
    private String email;
    
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    
    @OneToMany(targetEntity = Sale.class,
    		mappedBy = "customer")
    private Set<Sale> sales;
	
    public Customer() {
	}
    
    public int getId() {
		return id;
	}
	
    public void setId(int id) {
		this.id = id;
	}
	
    public String getName() {
		return name;
	}
	
    public void setName(String name) {
		this.name = name;
	}
	
    public String getEmail() {
		return email;
	}
	
    public void setEmail(String email) {
		this.email = email;
	}
	
    public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
    public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	
    public Set<Sale> getSales() {
		return sales;
	}
	
    public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}
}