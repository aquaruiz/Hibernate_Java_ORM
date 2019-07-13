package json.ex.domain.dtos.query6;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import json.ex.domain.dtos.CarDto;
import json.ex.domain.entities.Customer;

import java.math.BigDecimal;

public class SaleInfoDto {
    @Expose
    private CarDto car;

    private Customer customer;

    @Expose
    private String customerName;

    @Expose
    private double discount;

    @Expose
    @SerializedName(value = "price")
    private BigDecimal carPrice;

    @Expose
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