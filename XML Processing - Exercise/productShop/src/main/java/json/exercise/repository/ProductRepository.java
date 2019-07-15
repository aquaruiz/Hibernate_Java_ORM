package json.exercise.repository;

import json.exercise.domain.entities.Product;
import json.exercise.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByPriceGreaterThanAndPriceLessThanAndBuyerOrderByPriceAsc(BigDecimal lower, BigDecimal upper, User buyer);

    List<Product> findByBuyerIsNotNull();
}
