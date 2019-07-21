package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "categories")
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 3, max = 30)
    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Item> items;

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}