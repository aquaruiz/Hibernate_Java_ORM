package json.exercise.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.exercise.domain.dtos.ProductDto;
import json.exercise.domain.dtos.queryDtos.ProductInRangeDto;
import json.exercise.domain.dtos.queryDtos.SoldProductDto;
import json.exercise.domain.dtos.queryDtos.SuccessfullSellerDto;
import json.exercise.domain.entities.Category;
import json.exercise.domain.entities.Product;
import json.exercise.domain.entities.User;
import json.exercise.repository.CategoryRepository;
import json.exercise.repository.ProductRepository;
import json.exercise.repository.UserRepository;
import json.exercise.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final String FOLDER_PATH = "src" + File.separator +
                                    "main" + File.separator +
                                    "resources" + File.separator +
                                    "files" + File.separator;

    private final String FILE_PATH = FOLDER_PATH + File.separator +
                                    "products.json";
    private final String FILE_WRITE_PATH1 = FOLDER_PATH + File.separator +
                                    "products-in-range.json";
    private final String FILE_WRITE_PATH2 = FOLDER_PATH + File.separator +
                                    "users-sold-products.json";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = new ModelMapper();
    }

    public long getRecordsCount() {
        return this.productRepository.count();
    }

    @Transactional
    public void seedProducts() throws IOException {
        ProductDto[] productDtos = getFromJson(FILE_PATH);

        Product[] products = mappDtoToEntity(productDtos);

        if (validateEntities(products) == true){
            List<Category> allCategories = this.categoryRepository.findAll();
            List<User> allUsers = this.userRepository.findAll();

            Arrays.stream(products)
                    .forEach(p -> {
                        p.setCategories(getRandomCategory(allCategories));
                        p.setBuyer(getRandomUser(allUsers, true));
                        p.setSeller(getRandomUser(allUsers, false));
                    });

            this.productRepository
                    .saveAll(
                            Arrays.stream(products)
                            .collect(Collectors.toList())
                    );
        }
    }

    private User getRandomUser(List<User> users, Boolean nullable) {
        Random random = new Random();

        int num = random.nextInt(((users.size() - 1) - 1) + 1) + 1;

        if (nullable == true){
            if (Math.sqrt(num) == (int)Math.sqrt(num)){
                return null;
            }
        }

        return users.get(num);
    }

    private Set<Category> getRandomCategory(List<Category> categories) {
        Set<Category> productCategories = new HashSet<>();
        Random random = new Random();

        int tries = random.nextInt((15 - 0) + 1) + 0;

        for(int i = 0; i < tries; i++){
            int num = random.nextInt(((categories.size() - 1) - 1) + 1) + 1;
            productCategories.add(categories.get(num));
            System.out.println(num);
        }

        return productCategories;
    }

    private ProductDto[] getFromJson(String filePath) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        BufferedReader br = new BufferedReader(new FileReader(filePath));

        ProductDto[] productDtos = gson.fromJson(br, ProductDto[].class);
        br.close();

        return productDtos;
    }

    private void saveToJson(String filePath, List<? extends Object> dtos) throws IOException {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();

        FileWriter fw = new FileWriter(filePath);

        String jsonDto = gson.toJson(dtos);
        fw.write(jsonDto);
        fw.close();
    }

    private Product[] mappDtoToEntity(ProductDto[] productDtos) {
        Product[] products = Arrays.stream(productDtos)
                        .map(dto -> this.modelMapper.map(dto, Product.class))
                        .toArray(Product[]::new);
        return products;
    }

    private boolean validateEntities(Product[] products) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        for (Product product : products) {
            Set<ConstraintViolation<Product>> violations = validator.validate(product);

            if (violations.size() > 0){
                return false;
            }
        }

        return true;
    }

    @Override
    public void collectProductsInRange(int lowerboundary, int upperboundary) throws IOException {
        List<Product> products = this
                    .productRepository.
                    findAllByPriceGreaterThanAndPriceLessThanAndBuyerOrderByPriceAsc(
                            BigDecimal.valueOf(lowerboundary),
                            BigDecimal.valueOf(upperboundary),
                            null
                    );

        List<ProductInRangeDto> productInRangeDtos = products.stream()
                                                        .map(p -> modelMapper.map(p, ProductInRangeDto.class))
                                                        .collect(Collectors.toList());

        saveToJson(FILE_WRITE_PATH1, productInRangeDtos);
    }

    @Override
    @Transactional
    public void collectUsersWithSuccessfullySoldProducts() throws IOException {
        List<User> sellers = this.userRepository.getAllSellers();

        List<SuccessfullSellerDto> sellerDtos = sellers.stream()
                                .map(s -> modelMapper.map(s, SuccessfullSellerDto.class))
                                .collect(Collectors.toList());

        // implement inside lambda or in repo
        for (SuccessfullSellerDto sellerDto : sellerDtos) {
            List<SoldProductDto> sells = sellerDto.getSells();
            List<SoldProductDto> sellsToRemove = new ArrayList<>();

            for (SoldProductDto sell : sells) {
                if (sell.getBuyerLastName() == null&& sell.getBuyerFirstName() == null){
                    sellsToRemove.add(sell);
                }
            }

            sells.removeAll(sellsToRemove);
            sellerDto.setSells(sells);
        }

        saveToJson(FILE_WRITE_PATH2, sellerDtos);
    }
}