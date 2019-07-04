package bookshopsystemapp.repository;

import bookshopsystemapp.domain.entities.AgeRestriction;
import bookshopsystemapp.domain.entities.Author;
import bookshopsystemapp.domain.entities.Book;
import bookshopsystemapp.domain.entities.EditionType;
import bookshopsystemapp.domain.interfaces.ReducedBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findAllByReleaseDateAfter(LocalDate date);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeIsAndCopiesIsBefore(EditionType editionType, int count);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lower, BigDecimal upper);

    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate begin, LocalDate end);

    List<Book> findAllByTitleContains(String letters);

    List<Book> findAllByAuthorIn(List<Author> authors);

    @Query("SELECT b FROM books b WHERE LENGTH(b.title) > :minNum")
    List<Book> getAllByTitleLength(@Param(value = "minNum") int minNumber);

    @Query("SELECT CONCAT(b.author.firstName, ' ', b.author.lastName, ' - ', SUM(b.copies)) " +
            "FROM books AS b " +
            "GROUP BY b.author " +
            "ORDER BY SUM(b.copies) DESC")
    List<String> getTotalNumbersOfBookCopiesByAuthorDesc();

    @Query("SELECT b.title, b.editionType, b.ageRestriction, b.price " +
            "FROM books AS b " +
            "WHERE b.title LIKE :title ")
    List<ReducedBook> getBookByTitle(@Param(value = "title") String bookTitle);

    List<Book> findAllByTitleLike(String title);

    List<Book> findAllByCopiesIsBefore(int count);
}
