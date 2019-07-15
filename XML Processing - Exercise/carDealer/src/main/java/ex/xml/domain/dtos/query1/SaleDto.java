package ex.xml.domain.dtos.query1;

import com.google.gson.annotations.Expose;

public class SaleDto {
    @Expose
    private String carMake;

    @Expose
    private String customerName;

    @Expose
    private Double discountPercentage;

    public SaleDto() {
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
