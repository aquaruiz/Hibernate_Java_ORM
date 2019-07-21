package alararestaurant.domain.dtos;

import com.google.gson.annotations.Expose;

public class ItemImportDto {
    @Expose
    private String name;

    @Expose
    private double price;

    @Expose
    private String category;

    public ItemImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}