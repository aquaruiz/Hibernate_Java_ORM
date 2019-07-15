package xml.repository;

import xml.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM users u " +
            "INNER JOIN products p " +
            "ON u.id = p.seller.id " +
            "WHERE p.buyer.id IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.lastName ASC, u.firstName ASC")
    List<User> getAllSellers();

    @Query("SELECT u FROM users u " +
            "JOIN products p " +
            "ON p.seller.id = u.id " +
            "WHERE u.sells.size > 0 " +
            "GROUP BY u.id " +
            "ORDER BY u.sells.size DESC, u.lastName ASC")
    List<User> findAllSoldAtLeastOneProduct();
}
