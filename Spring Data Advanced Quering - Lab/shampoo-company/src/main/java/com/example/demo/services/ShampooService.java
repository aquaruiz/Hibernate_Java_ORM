package com.example.demo.services;

import com.example.demo.domain.entities.Ingredient;

import java.util.List;
import java.util.Set;

public interface ShampooService {

    List<String> selectShampooBySize(String sizeType);

    List<String> selectShampooBySizeAndLabel(String sizeType, String labelType);

    List<String> selectShampooByPrice(String priceString);

    Integer countShampoosByPrice(String boundryPriceString);

    List<String> selectShampoosByIngredients(Set<Ingredient> ingredientSet);

    List<String> getShampoosByIngredientsCount(Integer count);

    String selectIngredientNamePriceAndShampooBrandByName(String shampooBrandName);
}
