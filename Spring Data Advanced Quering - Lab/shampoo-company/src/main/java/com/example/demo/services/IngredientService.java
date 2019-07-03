package com.example.demo.services;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<String> selectIngredientByName(String startingLetters) {
        List<Ingredient> foundIngredients = this.ingredientRepository.findAllByNameStartsWith(startingLetters);
        return foundIngredients.stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }

    public List<String> selectIngredientByNameIn(List<String> ingredientsList) {
        List<Ingredient> foundIngredients = this.ingredientRepository.findAllByNameInOrderByPriceAsc(ingredientsList);
        return foundIngredients.stream()
                .map(s -> s.getName())
                .collect(Collectors.toList());
    }
}
