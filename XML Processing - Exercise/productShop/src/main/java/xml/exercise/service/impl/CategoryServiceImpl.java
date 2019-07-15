package xml.service.impl;

import xml.config.ExportService;
import xml.config.ImportService;
import xml.config.InputConstants;
import xml.config.OutputConstants;
import xml.domain.dtos.CategoriesDto;
import xml.domain.dtos.CategoryDto;
import xml.domain.dtos.queryDtos.query3.CategoryStatisticsDto;
import xml.domain.dtos.queryDtos.query3.RootCategoryDto;
import xml.domain.entities.Category;
import xml.repository.CategoryRepository;
import xml.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public long getRecordsCount() {

        return this.categoryRepository.count();
    }

    public void seedCategories() throws FileNotFoundException, JAXBException {
        CategoriesDto categoriesDto = ImportService.getFromXml(InputConstants.FILE_CATEGORIES, CategoriesDto.class);

        CategoryDto[] categoryDtos = categoriesDto.getCategoryDtos();
        List<Category> categories = ImportService.mappDtoToEntity(categoryDtos, Category.class);

        if (ImportService.areValidEntities(categories)){
            this.categoryRepository.saveAll(categories);
        }
    }

    @Override
    public void getStatisticsByProductsCount() throws IOException, JAXBException {
        List<List<String>> total = this.categoryRepository.getStatistics();

        RootCategoryDto dtoToXml = new RootCategoryDto();

        List<CategoryStatisticsDto> categoryStatisticsDtos = new ArrayList<>();
        total.forEach(
                t -> {
                    CategoryStatisticsDto catDto = new CategoryStatisticsDto();
                    catDto.setCategoryName(t.get(0));
                    catDto.setProductsCount(Integer.parseInt(t.get(1)));
                    catDto.setAveragePrice(new BigDecimal(t.get(2)));
                    catDto.setTotalRevenue(new BigDecimal(t.get(3)));
                    categoryStatisticsDtos.add(catDto);
                }
        );

        dtoToXml.setCategories(categoryStatisticsDtos);
        ExportService.saveToXml(OutputConstants.FILE_WRITE_PATH3, dtoToXml);
    }
}