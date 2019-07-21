package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        List<Category> categories = this.categoryRepository.findByItemsOrderThan();
        StringBuilder sBuilder = new StringBuilder();
        categories.forEach(
                c -> {
                    sBuilder.append(String.format("Name: %s",
                            c.getName()))
                            .append(System.lineSeparator());

                    c.getItems().forEach(
                            i -> {
                                sBuilder.append(String.format("--- Item Name: %s",
                                        i.getName()))
                                        .append(System.lineSeparator())
                                        .append(String.format("--- Item Price: %.2f",
                                                i.getPrice()))
                                        .append(System.lineSeparator())
                                        .append(System.lineSeparator());
                            }
                    );
                }
        );

        return sBuilder.toString();
    }
}
