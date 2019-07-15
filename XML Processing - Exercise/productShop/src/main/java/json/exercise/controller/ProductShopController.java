package json.exercise.controller;

import json.exercise.service.CategoryService;
import json.exercise.service.ProductService;
import json.exercise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

@Controller
public class ProductShopController implements CommandLineRunner {
    private final ProductService productService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final Scanner scanner;

    @Autowired
    public ProductShopController(ProductService productService, UserService userService, CategoryService categoryService) {
        this.productService = productService;
        this.userService = userService;
        this.categoryService = categoryService;

        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        populateDb();

        // query 1
//        this.getProductsInRange();

        // query 2
//        this.getUsersWithSuccessfullySoldProducts();
        
        // query 3
//        this.getCategoriesByProductsCount();

        // query 4
        this.getUsersAndProducts();
    }

    private void populateDb() throws IOException, JAXBException {
        if (this.categoryService.getRecordsCount() < 1){
            this.categoryService.seedCategories();
        }

        if (this.userService.getRecordsCount() < 1){
            this.userService.seedUsers();
        }

        if (this.productService.getRecordsCount() < 1){
            this.productService.seedProducts();
        }
    }

    private void getProductsInRange() throws IOException, JAXBException {
        System.out.print("Enter price range (e.g. 500 1000): ");
        Integer[] priceRange = Arrays.stream(scanner.nextLine()
                                            .split("\\s+"))
                                        .map(Integer::parseInt)
                                        .toArray(Integer[]::new);
        int lowerboundary = priceRange[0];
        int upperboundary = priceRange[1];

        this.productService.collectProductsInRange(lowerboundary, upperboundary);
    }

    private void getUsersWithSuccessfullySoldProducts() throws IOException, JAXBException {
        this.productService.collectUsersWithSuccessfullySoldProducts();
    }

    private void getCategoriesByProductsCount() throws IOException, JAXBException {
        this.categoryService.getStatisticsByProductsCount();
    }

    private void getUsersAndProducts() throws IOException, JAXBException {
        this.userService.getUsersAndSoldProducts();
    }
}