package com.example.demo.repositories;

import com.example.demo.domain.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByNameStartsWith(String letters);

    List<Ingredient> findAllByNameInOrderByPriceAsc(List<String> ingredientsList);

    Set<Ingredient> findAllByNameIn(List<String> ingredientsList);

    List<Ingredient> findAllByName(String ingredientName);

    @Transactional
    void deleteIngredientByName(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE ingredients i SET i.price = i.price * :rate")
    void increaseAllIngredientPriceBy(@Param(value = "rate") BigDecimal rate);


    @Transactional
    @Modifying
    @Query(value = "UPDATE ingredients i SET i.price = i.price * :rate WHERE i.name IN :names")
    void increaseAllIngredientPriceInNamesBy(@Param(value = "names") List<String> ingredientNamesList, @Param(value = "rate") BigDecimal rate);
}