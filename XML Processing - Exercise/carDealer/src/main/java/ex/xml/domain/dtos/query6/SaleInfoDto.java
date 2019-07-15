package ex.xml.domain.dtos.query6;

import ex.xml.domain.entities.Customer;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleInfoDto {
    @XmlElement(name = "car")
    private CarDto car;

    @XmlTransient
    private Customer customer;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement(name = "discount")
    private double discount;

    @XmlElement(name = "price")
    private BigDecimal carPrice;

    @XmlElement(name = "price-with-discount")
    private BigDecimal priceWithDiscount;

    public SaleInfoDto(CarDto car, Customer customer, String customerName, double discount, BigDecimal carPrice, BigDecimal priceWithDiscount) {
        this.car = car;
        this.customer = customer;
        this.customerName = customerName;
        this.discount = discount;
        this.carPrice = carPrice;
        this.priceWithDiscount = priceWithDiscount;
    }

    public SaleInfoDto() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public BigDecimal getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(BigDecimal carPrice) {
        this.carPrice = carPrice;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount() {
        boolean add5More = this.customer.isYoungDriver();
        BigDecimal disc = BigDecimal.valueOf(this.discount).divide(BigDecimal.valueOf(100));

        if (add5More){
            disc = disc.add(BigDecimal.valueOf(0.05));
        }

        this.priceWithDiscount = this.carPrice.subtract(this.carPrice.multiply(disc));
    }
}