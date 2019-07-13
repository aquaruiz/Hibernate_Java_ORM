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
}
