package xml.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "products")
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(nullable = false)
    @Size(min = 3)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(targetEntity = User.class, optional = true)
    private User buyer;

    @ManyToOne(targetEntity = User.class, optional = false)
    private User seller;

    @ManyToMany(targetEntity = Category.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "category_products",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<Category> categories;

    public Product() {
        this.categories = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        if(this.categories.contains(category)){
            return;
        }

        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        if(!this.categories.contains(category)){
            return;
        }

        this.categories.remove(category);
    }
}