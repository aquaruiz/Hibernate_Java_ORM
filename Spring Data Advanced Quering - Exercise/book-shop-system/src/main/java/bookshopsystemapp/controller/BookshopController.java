package bookshopsystemapp.controller;

import bookshopsystemapp.domain.entities.EditionType;
import bookshopsystemapp.service.AuthorService;
import bookshopsystemapp.service.BookService;
import bookshopsystemapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

@Controller
public class BookshopController implements CommandLineRunner {

    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;
    private final Scanner scanner;

    @Autowired
    public BookshopController(AuthorService authorService, CategoryService categoryService, BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... strings) throws Exception {
        this.populateDb();

        System.out.print("Please enter a task number: ");
        int taskNum = Integer.parseInt(scanner.nextLine());

        switch (taskNum){
            case 1:
                this.getBooksTitlesByAgeRestriction();
                break;
            case 2:
                this.getGoldenBooks();
                break;
            case 3:
                this.getBooksByPrice();
                break;
            case 4:
                this.getNotReleasedBooks();
                break;
            case 5:
                this.getBooksReleasedBeforeDate();
                break;
            case 6:
                this.doAuthorsSearch();
                break;
            case 7:
                this.doBookSearch();
                break;
            case 8:
                this.doBookTitlesSearch();
                break;
            case 9:
                this.countBooksByTitleLength();
                break;
            case 10:
                this.countTotalBookCopies();
                break;
            case 11:
                this.doReducedBook();
                break;
            case 12:
                this.increaseBookCopies();
                break;
            case 13:
                this.removeBooks();
                break;
            case 14:
                break;
            default:
                break;
        }
    }

    private void getBooksTitlesByAgeRestriction() {
        String ageRestrictionString = scanner.nextLine().toLowerCase();
        List<String> books = this.bookService.getBooksTitlesByAgeRestriction(ageRestrictionString);
        printResultList(books);
    }


    private void getGoldenBooks() {
        int copiesCount = 5000;
        EditionType editionType = EditionType.valueOf("GOLD");
        List<String> books = this.bookService.getBookByCount(editionType, copiesCount);
        printResultList(books);
    }

    private void getBooksByPrice() {
        int lowerPriceBoundary = 5;
        int upperPriceBoundary = 40;
        List<String> books = this.bookService.getBooksByPriceOutsideBoundaries(lowerPriceBoundary, upperPriceBoundary);
        printResultList(books);
    }

    private void getNotReleasedBooks() throws ParseException {
        System.out.print("Please enter year: ");
        String year = scanner.nextLine();
        List<String> books = this.bookService.getAllNotReleasedByYear(year);
        printResultList(books);
    }

    private void getBooksReleasedBeforeDate() {
        System.out.print("Enter your date in format d/M/yyyy: ");
        String dateString = scanner.nextLine();
        List<String> books = this.bookService.getBooksByReleaseDate(dateString);
        printResultList(books);
    }

    private void doAuthorsSearch() {
        System.out.print("Please enter letter(s): ");
        String letters = scanner.nextLine();
        List<String> authors = this.authorService.getAuthorsByNameEnds(letters);
        printResultList(authors);
    }

    private void doBookSearch() {
        System.out.print("Please enter letter(s): ");
        String letters = scanner.nextLine().toLowerCase();
        List<String> books = this.bookService.findBooksByNameContains(letters);
        printResultList(books);
    }

    private void doBookTitlesSearch() {
        System.out.print("Please enter letter(s): ");
        String letters = scanner.nextLine();
        List<String> books = this.bookService.findBooksByAuthorLastNameContains(letters);
        printResultList(books);
    }

    private void countBooksByTitleLength() {
        System.out.print("Enter desired min title length: ");
        int minNumber = Integer.parseInt(scanner.nextLine());
        int count = this.bookService.getCountTitlesLongerThan(minNumber);
        System.out.println(count);
        System.out.printf("There are %d books with longer title than %d symbols%n",
                count,
                minNumber);
    }

    private void countTotalBookCopies() {
        
    }

    private void doReducedBook() {
    }

    private void increaseBookCopies() {
    }

    private void removeBooks() {
    }

    private void populateDb() throws IOException {
        if (this.authorService.getRecordsCount() < 1){
            this.authorService.seedAuthors();
        }

        if (this.categoryService.getRecordsCount() < 1){
            this.categoryService.seedCategories();
        }

        if (this.bookService.getRecordsCount() < 1){
            this.bookService.seedBooks();
        }
    }

    private void printResultList(List<String> list) {
        list.forEach(System.out::println);
    }
}