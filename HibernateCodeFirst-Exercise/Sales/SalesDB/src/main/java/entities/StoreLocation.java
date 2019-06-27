package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "store_locations")
public class StoreLocation {
	@Id
	@GeneratedValue
    private int id;
	
	@Column(name = "location_name")
    private String locationName;
	
	@OneToMany(mappedBy = "storeLocation", targetEntity = Sale.class)
    private Set<Sale> sales;
	
	public StoreLocation() {
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLocationName() {
		return locationName;
	}
	
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public Set<Sale> getSales() {
		return sales;
	}
	
	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}
}
