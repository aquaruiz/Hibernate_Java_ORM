package json.exercise.domain.dtos.queryDtos.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootCategoryDto {
    @XmlElement(name = "category")
    List<CategoryStatisticsDto> categories;

    public RootCategoryDto() {
    }

    public List<CategoryStatisticsDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryStatisticsDto> categories) {
        this.categories = categories;
    }
}