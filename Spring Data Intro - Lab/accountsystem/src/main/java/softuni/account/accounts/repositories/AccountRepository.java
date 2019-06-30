package softuni.account.accounts.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import softuni.account.accounts.models.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
