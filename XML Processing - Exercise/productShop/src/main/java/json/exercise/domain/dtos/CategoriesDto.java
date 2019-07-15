package json.exercise.domain.dtos;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesDto implements Serializable {
    @XmlElement(name = "category")
    private CategoryDto[] categoryDtos;

    public CategoriesDto() {
    }

    public CategoryDto[] getCategoryDtos() {
        return categoryDtos;
    }

    public void setCategoryDtos(CategoryDto[] categoryDtos) {
        this.categoryDtos = categoryDtos;
    }
}