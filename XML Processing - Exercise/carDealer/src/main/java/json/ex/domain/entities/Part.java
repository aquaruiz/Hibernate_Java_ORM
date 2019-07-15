package json.ex.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "parts")
@Table(name = "parts")
public class Part extends BaseEntity{
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @ManyToOne(targetEntity = Supplier.class)
    private Supplier supplier;

    @ManyToMany(targetEntity = Car.class, mappedBy = "parts")
    private List<Car> cars;

    public Part() {
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}