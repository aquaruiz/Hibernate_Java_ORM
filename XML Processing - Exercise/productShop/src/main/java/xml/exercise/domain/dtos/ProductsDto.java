package xml.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsDto extends Object{
    @XmlElement(name = "product")
    private ProductDto[] products;

    public ProductsDto() {
    }

    public ProductDto[] getProducts() {
        return products;
    }

    public void setProducts(ProductDto[] products) {
        this.products = products;
    }
}