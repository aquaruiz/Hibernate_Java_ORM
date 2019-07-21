package alararestaurant.repository;

import alararestaurant.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);

    @Query(value = "select c.id, c.name from categories c " +
            "inner join items i " +
            "on c.id = i.category_id " +
            "GROUP BY c.id " +
            "ORDER BY count(i.id) DESC, sum(i.price) DESC",
        nativeQuery = true)
    List<Category> findByItemsOrderThan();
}
