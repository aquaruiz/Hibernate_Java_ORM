package model_mapper.lab.domain.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "authors")
@Table(name = "entity")
public class Employee extends BaseEntity {

    private String firstName;
    private String lastName;
    private double salary;
    private Date birthday;
    private Address address;

    private boolean isOnHoliday;

    private Employee managedBy;
    private Set<Employee> onChargeOf;

    public Employee() {
        this.onChargeOf = new HashSet<>();
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Column(name = "birth_day")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @OneToOne
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isOnHoliday() {
        return isOnHoliday;
    }

    public void setOnHoliday(boolean onHoliday) {
        isOnHoliday = onHoliday;
    }

//    @ManyToOne
    public Employee getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(Employee managedBy) {
        this.managedBy = managedBy;
    }

//    @OneToMany(mappedBy = "managed_by")
    public Set<Employee> getOnChargeOf() {
        return onChargeOf;
    }

    public void setOnChargeOf(Set<Employee> onChargeOf) {
        this.onChargeOf = onChargeOf;
    }
}
