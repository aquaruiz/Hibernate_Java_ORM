package mostwanted.repository;

import mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Optional<Town> findByName(String name);

    @Query(value = "SELECT t FROM towns t " +
            "WHERE t.racers.size > 0 " +
            "ORDER BY t.racers.size DESC, t.name ASC")
    List<Town> findByRacersIsNotNull();
}
