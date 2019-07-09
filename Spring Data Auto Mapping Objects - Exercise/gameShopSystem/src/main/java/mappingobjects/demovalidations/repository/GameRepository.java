package mappingobjects.demovalidations.repository;

import mappingobjects.demovalidations.domain.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findByTitle(String title);

    void deleteById(Integer id);
}
