package json.ex.domain.dtos.query1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashSet;
import java.util.Set;

public class OrderedCustomerDto {
    @Expose
    @SerializedName(value = "Id")
    private Integer id;

    @Expose
    @SerializedName(value = "Name")
    private String name;

    @Expose
    @SerializedName(value = "BirthDate")
    private String birthDate;

    @Expose
    @SerializedName(value = "IsYoungDriver")
    private boolean isYoungDriver;

    @Expose
    @SerializedName(value = "Sales")
    private Set<SaleDto> sales;

    public OrderedCustomerDto() {
        this.sales = new HashSet<>();
    }

    public OrderedCustomerDto(Integer id, String name, String birthDate, boolean isYoungDriver, Set<SaleDto> sales) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.sales = sales;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleDto> sales) {
        this.sales = sales;
    }
}