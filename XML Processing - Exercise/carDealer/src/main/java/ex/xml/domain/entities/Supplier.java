package ex.xml.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "suppliers")
@Table(name = "suppliers")
public class Supplier extends BaseEntity {
    private String name;
    private Boolean isImporter;

    @OneToMany(targetEntity = Part.class, mappedBy = "supplier", fetch = FetchType.EAGER)
    private Set<Part> parts;

    public Supplier() {
        this.parts = new HashSet<>();
    }

    public Supplier(String name, Boolean isImporter, Set<Part> parts) {
        this.name = name;
        this.isImporter = isImporter;
        this.parts = parts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getImporter() {
        return isImporter;
    }

    public void setImporter(Boolean importer) {
        isImporter = importer;
    }

    public Set<Part> getParts() {
        return parts;
    }

    public void setParts(Set<Part> parts) {
        this.parts = parts;
    }

    public void addPart(Part part) {
        this.parts.add(part);
    }
}