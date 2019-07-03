package com.example.demo.repositories;

import com.example.demo.domain.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartsWith(String letters);

    List<Ingredient> findAllByNameInOrderByPriceAsc(List<String> ingredientsList);

//    @Query(value = "SELECT i FROM Ingredient i WHERE i.name IN  :ingredients_list")
}
