package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cars")
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(columnDefinition = "DECIMAL(19, 2)")
    private double price;

    @Column(name = "year_of_production", nullable = false)
    private int yearOfProduction;

    @Column(name = "max_speed")
    private double maxSpeed;

    @Column(name = "zero_to_sixty")
    private double zeroToSixty;

    @ManyToOne(targetEntity = Racer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "racer_id")
    private Racer racer;

    @OneToMany(mappedBy = "car")
    private List<RaceEntry> entries;

    public Car() {
        this.entries = new ArrayList<>();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getZeroToSixty() {
        return zeroToSixty;
    }

    public void setZeroToSixty(double zeroToSixty) {
        this.zeroToSixty = zeroToSixty;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}