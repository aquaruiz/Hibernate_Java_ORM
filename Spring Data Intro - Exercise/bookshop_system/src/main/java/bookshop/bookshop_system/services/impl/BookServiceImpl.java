package bookshop.bookshop_system.services.impl;

import bookshop.bookshop_system.entities.*;
import bookshop.bookshop_system.repositories.AuthorRepository;
import bookshop.bookshop_system.repositories.BookRepository;
import bookshop.bookshop_system.repositories.CategoryRepository;
import bookshop.bookshop_system.services.BookService;
import bookshop.bookshop_system.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
@Transactional // OR cascadeType.ALL inside entity
public class BookServiceImpl implements BookService {
    private final static String BOOKS_FILE_PATH =
            "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator +
            "books.txt";
    public final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;
    public final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(CategoryRepository categoryRepository,
                           BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           FileUtil fileUtil) {

        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        if (this.bookRepository.count() > 0){
            return;
        }

        String[] books = this.fileUtil.fileContent(BOOKS_FILE_PATH);

        for (String s : books) {
            String[] params = s.split("\\s+");
            Book book = new Book();
            book.setAuthor(randomAuthor());

            EditionType editionType = EditionType.values()[Integer.parseInt(params[0])];
            book.setEditionType(editionType);

            LocalDate releaseDate = LocalDate.parse(params[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            book.setReleaseDate(releaseDate);

            book.setCopies(Integer.parseInt(params[2]));

            BigDecimal price = new BigDecimal(params[3]);
            book.setPrice(price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(params[4])];
            book.setAgeRestriction(ageRestriction);

            StringBuilder title = new StringBuilder();
            for (int i = 5; i < params.length; i++) {
                title.append(params[i]).append(" ");
            }
            book.setTitle(title.toString().trim());

            book.setCategories(this.randomCategories());

            bookRepository.saveAndFlush(book);
        }
    }

    private Author randomAuthor() {
        Random random = new Random();
        int index = random.nextInt((int) this.authorRepository.count());
        return this.authorRepository.getOne(index + 1);
    }

    private Category randomCategory() {
        Random random = new Random();
        int index = random.nextInt((int) this.categoryRepository.count());
        return this.categoryRepository.getOne(index + 1);
    }

    private  Set<Category> randomCategories(){
        Set<Category> categories = new HashSet<>();

        Random random = new Random();
        int index = random.nextInt((int) this.categoryRepository.count()) + 1;

        for (int i = 0; i < index; i++) {
            categories.add(randomCategory());
        }

        return categories;
    }
}
