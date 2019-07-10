package json.exercise.domain.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryDto {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    public CategoryDto() {
    }

    public CategoryDto(String name, BigDecimal price) {
        this.setName(name);
        this.setPrice(price);
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