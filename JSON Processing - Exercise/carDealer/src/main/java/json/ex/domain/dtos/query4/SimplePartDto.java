package json.ex.domain.dtos.query4;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SimplePartDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public SimplePartDto() {
    }

    public SimplePartDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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
}