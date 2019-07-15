package json.ex.domain.dtos.query5;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class TotalCustomerSalesDto {
    @Expose
    private String fullName;

    @Expose
    private Integer boughtCars;

    @Expose
    private BigDecimal spentMoney;

    public TotalCustomerSalesDto() {
    }

    public TotalCustomerSalesDto(String fullName, Integer boughtCars, BigDecimal spentMoney) {
        this.fullName = fullName;
        this.boughtCars = boughtCars;
        this.spentMoney = spentMoney;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Integer boughtCars) {
        this.boughtCars = boughtCars;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }
}