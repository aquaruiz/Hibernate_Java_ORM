package bookshopsystemapp.service;

import bookshopsystemapp.domain.entities.*;
import bookshopsystemapp.domain.interfaces.ReducedBook;
import bookshopsystemapp.repository.AuthorRepository;
import bookshopsystemapp.repository.BookRepository;
import bookshopsystemapp.repository.CategoryRepository;
import bookshopsystemapp.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static String BOOKS_FILE_PATH =
            "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator +
            "books.txt";

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, CategoryRepository categoryRepository, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() != 0) {
            return;
        }

        String[] booksFileContent = this.fileUtil.getFileContent(BOOKS_FILE_PATH);
        for (String line : booksFileContent) {
            String[] lineParams = line.split("\\s+");

            Book book = new Book();
            book.setAuthor(this.getRandomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(lineParams[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(lineParams[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            int copies = Integer.parseInt(lineParams[2]);
            book.setCopies(copies);

            BigDecimal price = new BigDecimal(lineParams[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(lineParams[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < lineParams.length; i++) {
                title.append(lineParams[i]).append(" ");
            }

            book.setTitle(title.toString().trim());

            Set<Category> categories = this.getRandomCategories();
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public List<String> getAllBooksTitlesAfter() {
        List<Book> books = this.bookRepository.findAllByReleaseDateAfter(LocalDate.parse("2000-12-31"));

        return books.stream().map(b -> b.getTitle()).collect(Collectors.toList());
    }

    @Override
    public Set<String> getAllAuthorsWithBookBefore() {
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(LocalDate.parse("1990-01-01"));

        return books.stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toSet());
    }

    private Author getRandomAuthor() {
        Random random = new Random();

        int randomId = random.nextInt((int) (this.authorRepository.count() - 1)) + 1;

        return this.authorRepository.findById(randomId).orElse(null);
    }

    private Set<Category> getRandomCategories() {
        Set<Category> categories = new LinkedHashSet<>();

        Random random = new Random();
        int length = random.nextInt(5);

        for (int i = 0; i < length; i++) {
            Category category = this.getRandomCategory();

            categories.add(category);
        }

        return categories;
    }

    private Category getRandomCategory() {
        Random random = new Random();
        int randomId = random.nextInt((int) (this.categoryRepository.count() - 1)) + 1;

        return this.categoryRepository.findById(randomId).orElse(null);
    }

    @Override
    public long getRecordsCount() {
        return this.bookRepository.count();
    }

    @Override
    public List<String> getBooksTitlesByAgeRestriction(String ageRestrictionString) {
        AgeRestriction ageRestriction = AgeRestriction.valueOf(ageRestrictionString.toUpperCase());
        List<Book> books = this.bookRepository.findAllByAgeRestriction(ageRestriction);

        return books.stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookByCount(EditionType editionType, int copiesCount) {
        List<Book> books = this.bookRepository
                .findAllByEditionTypeIsAndCopiesIsBefore(editionType, copiesCount);
        return books.stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByPriceOutsideBoundaries(int lowerPriceBoundary, int upperPriceBoundary) {
        BigDecimal lower = new BigDecimal(lowerPriceBoundary);
        BigDecimal upper = new BigDecimal(upperPriceBoundary);
        List<Book> books = this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lower, upper);
        return books.stream()
                .map(b -> String.format("%s - $%s",
                        b.getTitle(),
                        b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllNotReleasedByYear(String year) throws ParseException {
        int boundaryYear = Integer.parseInt(year);
        LocalDate startDate = LocalDate.of(boundaryYear, 1, 1);
        LocalDate endDate = LocalDate.of(boundaryYear, 12, 31);
        List<Book> books = this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(startDate, endDate);
        return books.stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByReleaseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        List<Book> books = this.bookRepository.findAllByReleaseDateBefore(localDate);
        return books.stream()
                .map(b -> String.format("%s %s %s",
                        b.getTitle(),
                        b.getEditionType().name(),
                        b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByNameContains(String letters) {
        List<Book> books = this.bookRepository.findAllByTitleContains(letters);
        return books.stream()
                .map(b -> b.getTitle())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findBooksByAuthorLastNameContains(String letters) {
        List<Author> authors = this.authorRepository.findAllByLastNameStartsWith(letters);
        List<Book> books = this.bookRepository.findAllByAuthorIn(authors);
        return books.stream()
                .map(b -> String.format("%s (%s %S)",
                        b.getTitle(),
                        b.getAuthor().getFirstName(),
                        b.getAuthor().getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public int getCountTitlesLongerThan(int minNumber) {
        List<Book> books = this.bookRepository.getAllByTitleLength(minNumber);
        return books.size();
    }

    @Override
    public List<String> getCopiesByAuthor() {
//        List<String> list = this.bookRepository.getTotalNumbersOfBookCopiesByAuthorDesc();
//        List<String> list = this.authorRepository.getCopiesByAuthorName();
        List<String> list = this.authorRepository.getCopiesByAuthorNameSecondTry();
        return list;
    }

    @Override
    public List<ReducedBook> getReducedBookByTitle(String bookTitle) {
        List<Book> books = this.bookRepository.findAllByTitleLike(bookTitle);
        return books.stream()
                .map(b -> {
                    ReducedBook r = new ReducedBook();
                    r.setTitle(b.getTitle());
                    r.setAgeRestriction(b.getAgeRestriction());
                    r.setEditionType(b.getEditionType());
                    r.setPrice(b.getPrice());
                    return  r;
                })
                .collect(Collectors.toList());
    }

    @Override
    public int increaseBookCountBySince(String countString, String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate boundaryDate = LocalDate.parse(dateString, formatter);

        int countToIncreaseBy = Integer.parseInt(countString);

        List<Book> booksToIncrease = this.bookRepository.findAllByReleaseDateAfter(boundaryDate);
        booksToIncrease.forEach(b -> b.setCopies(b.getCopies() + countToIncreaseBy));
        this.bookRepository.saveAll(booksToIncrease);
        this.bookRepository.flush();

        return booksToIncrease.size() * countToIncreaseBy;
    }

    @Override
    @Transactional
    public int deleteBooksWithCopiesUnder(String countString) {
        int countBoundary = Integer.parseInt(countString);
        List<Book> booksToDelete = this.bookRepository.findAllByCopiesIsBefore(countBoundary);

        this.bookRepository.deleteInBatch(booksToDelete);
        this.bookRepository.flush();
        return booksToDelete.size();
    }

    @Override
    public int callProcedure(String firstName, String lastName) {
//        int output = this.bookRepository.callProcedure(firstName, lastName);
        int output = this.bookRepository.callProcedureSecondTry(firstName, lastName);
        return output;
    }
}