package app.ccb.repositories;

import app.ccb.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByFirstNameAndLastName(String fName, String lName);

    @Query(value = "SELECT e FROM employees e " +
            "WHERE e.clients.size > 0 " +
            "ORDER BY e.clients.size DESC, e.id ASC")
    List<Employee> findAllByClients();
}
