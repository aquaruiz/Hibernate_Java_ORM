package bookshop.bookshop_system.services.impl;

import bookshop.bookshop_system.entities.Category;
import bookshop.bookshop_system.repositories.CategoryRepository;
import bookshop.bookshop_system.services.CategoryService;
import bookshop.bookshop_system.util.FileUtil;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private  final String CATEGORY_FILE_PATH =
                        "src" + File.separator +
                        "main" + File.separator +
                        "resources" + File.separator +
                        "files" + File.separator +
                        "categories.txt";

    public CategoryServiceImpl(CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategory() throws IOException {
        if (this.categoryRepository.count() > 0){
            return;
        }

        String[] categories = this.fileUtil.fileContent(CATEGORY_FILE_PATH);

        for (String s : categories) {
            Category category = new Category();
            category.setName(s);

            this.categoryRepository.saveAndFlush(category);
        }
    }
}
