package model_mapper.lab.domain.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "addresses")
@Table(name = "addresses")
public class Address extends BaseEntity {
    private String city;
    private String country;
    private String street;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return String.format("%s, %s --> %s",
                this.getCity(),
                this.getCountry(),
                this.getStreet());
    }
}
