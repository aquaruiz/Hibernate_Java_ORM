package com.example.demo.repositories;

import com.example.demo.domain.entities.Ingredient;
import com.example.demo.domain.entities.Label;
import com.example.demo.domain.entities.Shampoo;
import com.example.demo.domain.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {
    List<Shampoo> findAllByBrandAndSize(String brand, Size size);

    List<Shampoo> findAllBySizeOrderById(Size size);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price);

    Integer countAllByPriceBefore(BigDecimal price);

    @Query(value = "SELECT s FROM shampoos s JOIN s.ingredients i WHERE i IN :ingredients_set")
    List<Shampoo> findAllByIngredientsIn(@Param(value = "ingredients_set") Set<Ingredient> ingredientSet);

    @Query(value = "SELECT s FROM shampoos s WHERE s.ingredients.size < :count")
    List<Shampoo> findAllByIngredients(@Param(value = "count") Integer count);

    @Query(value = "SELECT SUM(i.price) FROM shampoos s JOIN s.ingredients i WHERE s.brand LIKE :brand")
    BigDecimal getTotalIngredientCostsByShampooBrand(@Param(value = "brand") String brand);
}