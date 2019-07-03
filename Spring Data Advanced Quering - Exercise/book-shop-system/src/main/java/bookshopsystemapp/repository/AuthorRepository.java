package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAllByFirstName(String name);

    List<Author> findAllByFirstNameEndsWith(String letters);

    List<Author> findAllByLastNameStartsWith(String letters);

    @Query(value = "select CONCAT_WS(' ', a.first_name, a.last_name, '-', sum(b.copies)) \n" +
            "from authors a \n" +
            "inner join books b on a.id = b.author_id \n" +
            "group by a.id \n" +
            "order by sum(b.copies) DESC",
            nativeQuery = true)
    List<String> getCopiesByAuthorName();

    @Query("SELECT CONCAT(a.firstName, ' ', a.lastName, ' - ', sum(b.copies)) " +
            "FROM books b " +
            "JOIN b.author a " +
            "GROUP BY b.author.id " +
            "ORDER BY SUM(b.copies) DESC")
    List<String> getCopiesByAuthorNameSecondTry();
}