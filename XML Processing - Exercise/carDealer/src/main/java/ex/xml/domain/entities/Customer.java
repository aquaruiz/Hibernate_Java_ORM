package ex.xml.domain.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "customers")
@Table(name = "customers")
public class Customer extends BaseEntity {
    private String name;
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver")
    private boolean isYoungDriver; // young if experience < 2 years
                                    // + 5 % price off

    @OneToMany(targetEntity = Sale.class, mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Sale> sales;

    public Customer() {
        this.sales = new HashSet<>();
    }

    public Customer(String name, LocalDateTime birthDate, boolean isYoungDriver, Set<Sale> sales) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

//    public void setBirthDate(String dateOfBirth) {
//        this.birthDate = LocalDateTime.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}