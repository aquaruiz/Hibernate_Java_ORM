package json.ex.domain.entities;

import javax.persistence.*;

@Entity(name = "sales")
@Table(name = "sales")
public class Sale extends BaseEntity {
    @OneToOne(targetEntity = Car.class)
    private Car car;

    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;

    @Column(name = "discount")
    private Double discountPercentage;

    public Sale() {
        setDiscountPercentage(0d);
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getDiscountPercentage() {
        if (this.customer.isYoungDriver()){
            return this.discountPercentage + 5;
        }

        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "car=" + car +
                ", customer=" + customer +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}