package xml.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "categories")
@Table(name = "categories")
public class Category extends BaseEntity {
    @Size(min = 5, max = 15)
    private String name;

    @ManyToMany(targetEntity = Product.class, mappedBy = "categories", cascade = CascadeType.PERSIST)
    private Set<Product> products;

    public Category() {
        this.products = new HashSet<>();
    }

    public Category(@Size(min = 5, max = 15) String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}