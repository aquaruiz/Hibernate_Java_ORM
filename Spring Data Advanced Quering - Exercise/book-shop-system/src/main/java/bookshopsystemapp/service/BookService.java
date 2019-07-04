package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.EditionType;
import bookshopsystemapp.domain.interfaces.ReducedBook;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface BookService {

    void seedBooks() throws IOException;

    List<String> getAllBooksTitlesAfter();

    Set<String> getAllAuthorsWithBookBefore();

    long getRecordsCount();

    List<String> getBooksTitlesByAgeRestriction(String ageRestrictionString);

    List<String> getBookByCount(EditionType editionType,int copiesCount);

    List<String> getBooksByPriceOutsideBoundaries(int lowerPriceBoundary, int upperPriceBoundary);

    List<String> getAllNotReleasedByYear(String year) throws ParseException;

    List<String> getBooksByReleaseDate(String dateString);

    List<String> findBooksByNameContains(String letters);

    List<String> findBooksByAuthorLastNameContains(String letters);

    int getCountTitlesLongerThan(int minNumber);

    List<String> getCopiesByAuthor();

    List<ReducedBook> getReducedBookByTitle(String bookTitle);

    int increaseBookCountBySince(String countString, String dateString);

    int deleteBooksWithCopiesUnder(String countString);

    int callProcedure(String firstName, String lastName);
}
