package mappingobjects.demovalidations.repository;

import mappingobjects.demovalidations.domain.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Integer> {

}
