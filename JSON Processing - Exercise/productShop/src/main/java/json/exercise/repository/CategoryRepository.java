package json.exercise.repository;

import json.exercise.domain.dtos.queryDtos.CategoryStatisticsDto;
import json.exercise.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "select  cat.name, count(p.id), avg(p.price), sum(p.price) from categories cat " +
            "inner join category_products cp " +
            "on cat.id = cp.category_id " +
            "inner join products p " +
            "on cp.product_id = p.id " +
            "group by cat.name " +
            "order by count(p.id) desc", nativeQuery = true)
    List<List<String>> getStatistics();
}
