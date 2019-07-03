package com.example.demo.services;

import com.example.demo.domain.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface IngredientService {
  List<String> selectIngredientByName(String startingLetters);

  List<String> selectIngredientByNameIn(List<String> ingredientsList);

  Set<Ingredient> getIngredientsByName(List<String> ingredientsList);

  void deleteIngredientsByName(String ingredientName);

  void increaseAllPricesByRate(BigDecimal increaseRate);

  void increaseAllPricesInNamesByRate(List<String> ingredientNamesList, BigDecimal increaseRate);
}