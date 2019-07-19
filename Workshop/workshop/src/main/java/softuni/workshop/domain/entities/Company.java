package softuni.workshop.domain.entities;

import org.hibernate.validator.constraints.EAN;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "companies")
@Table(name = "companies")
public class Company extends BaseId {
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "company")
    private List<Project> projects;
    public Company() {
        projects = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}