package json.exercise.domain.dtos.queryDtos.query4;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "sold-products ")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductsDto {
    @XmlAttribute(name = "count")
    private int count;
    @XmlElement(name = "product")
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