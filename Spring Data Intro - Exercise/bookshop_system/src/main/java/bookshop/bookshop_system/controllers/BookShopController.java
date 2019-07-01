package bookshop.bookshop_system.controllers;

import bookshop.bookshop_system.services.AuthorService;
import bookshop.bookshop_system.services.BookService;
import bookshop.bookshop_system.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookShopController implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public BookShopController(
            AuthorService authorService,
            CategoryService categoryService,
            BookService bookService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.authorService.seedAuthors();
        this.categoryService.seedCategory();
        this.bookService.seedBooks();
//        getBookTitles();
//        getAuthorNames();
//        getOrderedAuthors();
        getAllPowellBooks();
    }

    private void getAllPowellBooks() {
        List<String> books = this.bookService.findAllBooksByAuthor();
        books.forEach(System.out::println);
    }

    private void getOrderedAuthors() {
        List<String> authors = this.authorService.getOrderedAuthors();
        authors.forEach(System.out::println);
    }

    private void getBookTitles() {
        List<String> titles = this.bookService.findAllTitles();
        titles.forEach(System.out::println);
    }

    private void getAuthorNames() {
        List<String> authors = this.bookService.findAllAuthors();
        authors.forEach(System.out::println);
    }
}
