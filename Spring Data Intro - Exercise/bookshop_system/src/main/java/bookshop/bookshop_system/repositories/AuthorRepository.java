package bookshop.bookshop_system.repositories;

import bookshop.bookshop_system.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findALLBy();

    Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
