package softuni.account.accounts.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import softuni.account.accounts.models.User;

import java.util.EnumMap;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<EnumMap> getByUsername(String username);

    Optional<Object> findByUsername(String username);
}
