package json.exercise.domain.dtos.queryDtos.query3;

import com.google.gson.annotations.Expose;
import json.exercise.domain.dtos.ProductDto;

import java.util.ArrayList;
import java.util.List;

public class SoldProductsDto {
    @Expose
    private int count;
    @Expose
    private List<ProductDto> products;

    public SoldProductsDto() {
        this.count = 0;
        this.products = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public void addProducts(ProductDto product) {
        if (this.products.contains(product)){
            return;
        }

        this.products.add(product);
    }

    public void increaseCountBy(int num){
        this.setCount(this.getCount() + num);
    }
}