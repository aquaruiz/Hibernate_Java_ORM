package alararestaurant.service;

import alararestaurant.config.Constants;
import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private Gson gson;

    public ItemServiceImpl(ItemRepository itemRepository,
                           CategoryRepository categoryRepository,
                           FileUtil fileUtil,
                           ValidationUtil validationUtil,
                           ModelMapper modelMapper, Gson gson) {

        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        return fileUtil.readFile(Constants.ITEMS_IMPORT_PATH);
    }

    @Override
    public String importItems(String items) throws FileNotFoundException {
        StringBuilder sBuilder = new StringBuilder();
        ItemImportDto[] itemImportDtos = fileUtil.parseJSON(gson, Constants.ITEMS_IMPORT_PATH, ItemImportDto[].class);

        Arrays.stream(itemImportDtos)
            .forEach(i -> {
                Item saved = itemRepository.findByName(i.getName()).orElse(null);

                if (saved != null){
                    return;
                }

                Item newItem = modelMapper.map(i, Item.class);
                if (!validationUtil.isValid(newItem)){
                    sBuilder.append("Invalid data format.")
                        .append(System.lineSeparator());
                    return;
                }

                String categoryName = i.getCategory();
                Category newCategory = categoryRepository.findByName(categoryName).orElse(null);
                if (newCategory == null){
                    newCategory = new Category();
                    newCategory.setName(categoryName);

                    if (!validationUtil.isValid(newCategory)){
                        return;
                    }

                    newCategory = categoryRepository.saveAndFlush(newCategory);
                }

                newItem.setCategory(newCategory);
                itemRepository.saveAndFlush(newItem);
                sBuilder.append(String.format("Record %s successfully imported.",
                        i.getName()))
                        .append(System.lineSeparator());
                return;
            });

        return sBuilder.toString();
    }
}