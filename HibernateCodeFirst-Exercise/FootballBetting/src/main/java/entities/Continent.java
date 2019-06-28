package entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "continents")
public class Continent {
	private long id;	
    private String name;
    private Set<Country> countries;

    public Continent() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(
            name = "continents_countries",
            joinColumns = @JoinColumn(
                    name = "continent_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "country_id",
                    referencedColumnName = "id")
    )
    public Set<Country> getCountries() {
        return countries;
    }
    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }
}