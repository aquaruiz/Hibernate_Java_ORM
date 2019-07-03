package com.example.demo.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.example.demo.domain.entities.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import org.springframework.stereotype.Service;


@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<String> selectIngredientByName(String startingLetters) {
        List<Ingredient> foundIngredients = this.ingredientRepository
                .findAllByNameStartsWith(startingLetters);
        return foundIngredients.stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }

    public List<String> selectIngredientByNameIn(List<String> ingredientsList) {
        List<Ingredient> foundIngredients = this.ingredientRepository
                .findAllByNameInOrderByPriceAsc(ingredientsList);
        return foundIngredients.stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }

    public Set<Ingredient> getIngredientsByName(List<String> ingredientsList) {
        Set<Ingredient> ingredients = this.ingredientRepository
                .findAllByNameIn(ingredientsList);
        return ingredients;
    }

    public void deleteIngredientsByName(String ingredientName) {
        List<Ingredient> ingredientsDelete = this.ingredientRepository.findAllByName(ingredientName);
        if (ingredientsDelete.size() > 0) {
            this.ingredientRepository.deleteIngredientByName(ingredientName);
        }
    }

    public void increaseAllPricesByRate(BigDecimal increaseRate) {
        BigDecimal rate = new BigDecimal(1).add(increaseRate);
        this.ingredientRepository.increaseAllIngredientPriceBy(rate);
    }

    public void increaseAllPricesInNamesByRate(
            List<String> ingredientNamesList,
            BigDecimal increaseRate) {
        BigDecimal rate = new BigDecimal(1).add(increaseRate);
        this.ingredientRepository.increaseAllIngredientPriceInNamesBy(ingredientNamesList, rate);
    }
}