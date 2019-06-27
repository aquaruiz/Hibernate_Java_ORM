package entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale {
	@Id
	@GeneratedValue
    private int id;

	@ManyToOne(targetEntity = Product.class)
	@JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
    
	@ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    
	@ManyToOne(targetEntity = StoreLocation.class)
    @JoinColumn(name = "store_location_id", referencedColumnName = "id")
    private StoreLocation storeLocation;
    
	private Date date;
	
	public Sale() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public StoreLocation getStoreLocation() {
		return storeLocation;
	}
	
	public void setStoreLocation(StoreLocation storeLocation) {
		this.storeLocation = storeLocation;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
}
