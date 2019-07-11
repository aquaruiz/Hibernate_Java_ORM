package json.exercise.domain.dtos.queryDtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryStatisticsDto {
    @Expose
    private String categoryName;

    @Expose
    private  int productsCount;

    @Expose
    private BigDecimal averagePrice;

    @Expose
    private BigDecimal totalRevenue;

    public CategoryStatisticsDto() {
    }

    public CategoryStatisticsDto(String categoryName, int productsCount, BigDecimal averagePrice, BigDecimal totalRevenue) {
        this.categoryName = categoryName;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}