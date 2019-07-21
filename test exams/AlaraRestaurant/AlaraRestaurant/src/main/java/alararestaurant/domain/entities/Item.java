package alararestaurant.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "items")
@Table(name = "items")
public class Item extends BaseEntity {
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 30)
    private String name;

    @ManyToOne(targetEntity = Category.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(nullable = false, columnDefinition = "DECIMAL(19, 2)", precision = 2)
    @Positive
    private double price;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;

    public Item() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}