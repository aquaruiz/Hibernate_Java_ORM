package json.exercise.domain.dtos.queryDtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductInRangeDto {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    private String sellerFirstName;

    private String sellerLastName;

    @Expose
    private String seller;

    public ProductInRangeDto() {
    }

    public ProductInRangeDto(String name, BigDecimal price, String sellerFirstName, String sellerLastName, String seller) {
        this.name = name;
        this.price = price;
        this.sellerFirstName = sellerFirstName;
        this.sellerLastName = sellerLastName;
        this.seller = seller;
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

    public String getSellerFirstName() {
        return sellerFirstName;
    }

    public void setSellerFirstName(String sellerFirstName) {
        this.sellerFirstName = sellerFirstName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public void setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}