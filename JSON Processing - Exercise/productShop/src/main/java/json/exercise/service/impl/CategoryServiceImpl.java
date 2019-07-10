package json.exercise.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.exercise.domain.dtos.CategoryDto;
import json.exercise.domain.entities.Category;
import json.exercise.repository.CategoryRepository;
import json.exercise.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final String FILE_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator +
            "categories.json";
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = new ModelMapper();
    }

    public long getRecordsCount() {

        return this.categoryRepository.count();
    }

    public void seedCategories() throws FileNotFoundException {
        CategoryDto[] categoryDtos = getFromJson(FILE_PATH);

        Category[] categories = mappDtoToEntity(categoryDtos);

        System.out.println(categoryDtos.length);
        System.out.println(categories.length);

        if (validateEntities(categories) == true){
            this.categoryRepository
                    .saveAll(
                            Arrays.stream(categories)
                            .collect(Collectors.toList())
                    );
            System.out.println("Categories OK");
        }
    }

    private CategoryDto[] getFromJson(String filePath) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        CategoryDto[] categoryDtos = gson.fromJson(br, CategoryDto[].class);
        return categoryDtos;
    }

    private Category[] mappDtoToEntity(CategoryDto[] categoryDtos) {
        Category[] categories = Arrays.stream(categoryDtos)
                .map(dto -> this.modelMapper.map(dto, Category.class))
                .toArray(Category[]::new);
        return categories;
    }

    private boolean validateEntities(Category[] categories) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        for (Category category : categories) {
            Set<ConstraintViolation<Category>> violations = validator.validate(category);

            if (violations.size() > 0){
                return false;
            }
        }

        return true;
    }
}