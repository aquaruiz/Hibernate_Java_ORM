package json.ex.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cars")
@Table(name = "cars")
public class Car extends BaseEntity{
    private String make;
    private String model;

    @Column(name = "travelled_distance")
    private Long travelledDistance; // in km

    @ManyToMany(targetEntity = Part.class)
    @JoinTable(name = "parts_cars",
    joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "part_id", referencedColumnName = "id"))
    private List<Part> parts;
    private BigDecimal price;

    @OneToOne(targetEntity = Sale.class, mappedBy = "car")
    private Sale sale;

    public Car() {
        this.parts = new ArrayList<>();
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public void addPart(Part part) {
        this.parts.add(part);
    }

    public BigDecimal getPrice() {
        BigDecimal price = new BigDecimal("0");
        for (Part part : parts) {
            price = price.add(part.getPrice());
        }

        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}