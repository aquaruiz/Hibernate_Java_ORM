package bookshop.bookshop_system.controllers;

import bookshop.bookshop_system.services.AuthorService;
import bookshop.bookshop_system.services.BookService;
import bookshop.bookshop_system.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class BookShopController implements CommandLineRunner {
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private  final BookService bookService;

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
    }
}
