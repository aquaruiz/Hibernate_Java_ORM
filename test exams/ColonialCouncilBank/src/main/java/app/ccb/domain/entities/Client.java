package app.ccb.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity(name = "clients")
@Table(name = "clients")
public class Client extends BaseEntity {
    @Column(name = "full_name", nullable = false)
    private String fullName;

    private int age;

    @OneToOne(mappedBy = "client")
    private BankAccount bankAccount;

    @ManyToMany(targetEntity = Employee.class, cascade = ALL)
    @JoinTable(name = "clients_employees",
        joinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"))
    private Set<Employee> employees;

    public Client() {
        this.employees = new HashSet<>();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }
}