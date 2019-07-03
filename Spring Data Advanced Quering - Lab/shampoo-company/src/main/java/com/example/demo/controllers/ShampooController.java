package com.example.demo.controllers;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.services.IngredientService;
import com.example.demo.services.IngredientServiceImpl;
import com.example.demo.services.ShampooService;
import com.example.demo.services.ShampooServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Controller
public class ShampooController implements CommandLineRunner {
    private final ShampooService shampooService;
    private final IngredientService ingredientService;
    private final Scanner scanner;

    public ShampooController(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        // Task 1
//        this.selectShampooBySize();

        // Task 2
//        this.selectShampooBySizeAndLabel();

        // Task 3
//        this.selectShampoosByPrice();

        // Task 4
//        this.selectIngredientByNameStarts();

        // Task 5
//        this.selectIngredientByNameIn();

        // Task 6
//        this.countShampoosByPrice();

        // Task 7
//        this.selectShampoosByIngredients();

        // Task 8
//        this.selectShampoosByIngredientsCount();

        // Task 9
//       this.selectIngredientNameAndShampooBrandByName();
       
        // Task 10
//        this.deleteIngredientsByName();
        
        // Task 11
//        this.updateIngredientsByPrice();

        // Task 12
//        this.updateIngredientsPricesBbyNames();
    }

    private void updateIngredientsPricesBbyNames() {
        List<String> ingredientNamesList = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("")){
            ingredientNamesList.add(line);
        }

        BigDecimal increaseRate = new BigDecimal(0.1);
        this.ingredientService.increaseAllPricesInNamesByRate(ingredientNamesList, increaseRate);
    }

    private void updateIngredientsByPrice() {
        BigDecimal increaseRate = new BigDecimal(0.1);
        this.ingredientService.increaseAllPricesByRate(increaseRate);
    }

    private void deleteIngredientsByName() {
        String ingredientName = scanner.nextLine();

        this.ingredientService.deleteIngredientsByName(ingredientName);
    }

    private void selectIngredientNameAndShampooBrandByName() {
        String shampooBrandName = scanner.nextLine();

        String totalIngredientsPrice = this.shampooService
                .selectIngredientNamePriceAndShampooBrandByName(shampooBrandName);
        System.out.println(totalIngredientsPrice);
    }

    private void selectShampoosByIngredientsCount() {
        Integer count = Integer.parseInt(scanner.nextLine());

        List<String> shampoos = this.shampooService.getShampoosByIngredientsCount(count);
        shampoos.forEach(System.out::println);
    }

    private void selectShampoosByIngredients() {
        List<String> ingredientsList = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("")){
            ingredientsList.add(line);
        }

        Set<Ingredient> ingredientSet = this.ingredientService.getIngredientsByName(ingredientsList);
        List<String> shampoos = this.shampooService.selectShampoosByIngredients(ingredientSet);
        shampoos.forEach(System.out::println);
    }

    private void countShampoosByPrice() {
        String boundryPriceString = scanner.nextLine();
        Integer count = this.shampooService.countShampoosByPrice(boundryPriceString);
        System.out.println(count);
    }

    private void selectIngredientByNameIn() {
        List<String> ingredientsList = new ArrayList<>();
        String line;
        while (!(line = scanner.nextLine()).equals("")){
            ingredientsList.add(line);
        }

        List<String> ingredients = this.ingredientService.selectIngredientByNameIn(ingredientsList);
        ingredients.forEach(System.out::println);
    }

    private void selectIngredientByNameStarts() {
        String startingLetters = scanner.nextLine();
        List<String> ingredients = this.ingredientService.selectIngredientByName(startingLetters);

        ingredients.forEach(System.out::println);
    }

    private void selectShampoosByPrice() {
        String priceString = scanner.nextLine();
        List<String> shampoos = this.shampooService.selectShampooByPrice(priceString);

        shampoos.forEach(System.out::println);
    }

    private void selectShampooBySizeAndLabel() {
        String sizeType = scanner.nextLine();
        String labelType = scanner.nextLine();
        List<String> shampoos = this.shampooService.selectShampooBySizeAndLabel(sizeType, labelType);

        shampoos.forEach(System.out::println);
    }

    private void selectShampooBySize() {
        String sizeType = scanner.nextLine();
        List<String> shampoos = this.shampooService.selectShampooBySize(sizeType);

        shampoos.forEach(System.out::println);
    }
}