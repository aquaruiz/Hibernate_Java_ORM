package alararestaurant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "positions")
@Table(name = "positions")
public class Position extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 30)
    private String name;

    @OneToMany(mappedBy = "position")
    private List<Employee> employees;

    public Position() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}