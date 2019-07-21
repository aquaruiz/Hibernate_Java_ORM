package alararestaurant.repository;

import alararestaurant.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query(value = "SELECT o FROM orders o " +
            "WHERE o.employee.position.name LIKE 'Burger Flipper' " +
            "ORDER BY o.employee.name ASC, o.id ASC")
    List<Order> findAllByBurgerFlipers();
}
