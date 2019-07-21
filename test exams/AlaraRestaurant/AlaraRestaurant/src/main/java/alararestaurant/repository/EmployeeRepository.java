package alararestaurant.repository;

import alararestaurant.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByName(String name);

    @Query(value = "SELECT e FROM employees e " +
            "WHERE e.position.name LIKE 'Burger Flipper' " +
            "AND e.orders.size > 0 " +
            "ORDER BY e.name ASC")
    List<Employee> findAllBurgerFlipers();

}