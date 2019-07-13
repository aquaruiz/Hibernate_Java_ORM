package json.ex.repository;

import json.ex.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("SELECT c FROM customers c " +
            "ORDER BY c.birthDate ASC, c.isYoungDriver DESC")
    List<Customer> getAllOrderedByBirthDateAsc();

    @Query(value = "SELECT c.name, c.sales.size, SUM(ca.price) FROM customers c " +
            "JOIN sales s " +
            "on c.id = s.customer.id " +
            "JOIN cars ca " +
            "on s.car.id = ca.id " +
            "WHERE c.sales.size > 0 " +
            "GROUP BY c.id " +
            "ORDER BY SUM(ca.price) DESC, c.sales.size DESC")
    List<List<String>> getAllBySalesAndOrder();

    @Query(value = "SELECT c FROM customers c " +
            "WHERE c.sales.size > 0")
    List<Customer> getAllBySalesAndOrder1();
}
