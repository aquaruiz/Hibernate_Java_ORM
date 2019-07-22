package app.ccb.repositories;

import app.ccb.domain.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByFullName(String fullName);

    @Query(value = "SELECT c FROM clients c " +
            "ORDER BY c.bankAccount.cards.size DESC ")
    List<Client> findClientByBankCars();
}
