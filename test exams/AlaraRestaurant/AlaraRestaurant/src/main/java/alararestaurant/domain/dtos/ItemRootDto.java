package alararestaurant.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemRootDto {
    @XmlElement(name = "item")
    private List<ItemImportDto2> itemsDtos;

    public ItemRootDto() {
    }

    public List<ItemImportDto2> getItemsDtos() {
        return itemsDtos;
    }

    public void setItemsDtos(List<ItemImportDto2> itemsDtos) {
        this.itemsDtos = itemsDtos;
    }
}