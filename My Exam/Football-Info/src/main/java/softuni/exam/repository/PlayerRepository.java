package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
    @Query(value = "SELECT p FROM players p " +
            "WHERE p.team.name LIKE :name " +
            "ORDER BY p.id")
    List<Player> findAllByTeamName(@Param(value = "name") String teamName);

    List<Player> findAllBySalaryGreaterThanOrderBySalaryDesc(BigDecimal minSalary);
}
