package json.exercise.domain.dtos.queryDtos.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootProductsDto {
    @XmlElement(name = "product")
    private List<SoldProductDto> sells;

    public RootProductsDto() {
    }

    public List<SoldProductDto> getSells() {
        return sells;
    }

    public void setSells(List<SoldProductDto> sells) {
        this.sells = sells;
    }
}