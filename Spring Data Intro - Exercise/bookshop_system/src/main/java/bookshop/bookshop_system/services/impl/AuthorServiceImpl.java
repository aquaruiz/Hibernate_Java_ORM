package bookshop.bookshop_system.services.impl;

import bookshop.bookshop_system.entities.Author;
import bookshop.bookshop_system.repositories.AuthorRepository;
import bookshop.bookshop_system.services.AuthorService;
import bookshop.bookshop_system.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AuthorServiceImpl implements AuthorService {
    private  final  static String AUTHOR_FILE_PATH =
                    "src" + File.separator +
                    "main" + File.separator +
                    "resources" + File.separator +
                    "files" + File.separator +
                    "authors.txt";
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (this.authorRepository.count() > 0){
            return; // ?? needed
        }


        String[] authors = this.fileUtil.fileContent(AUTHOR_FILE_PATH);

        for (String s : authors) {
            String[] params = s.split("\\s+");
            Author author = new Author();
            if (params.length == 1){
                author.setLastName(params[0]);
            } else {
                author.setFirstName(params[0]);
                author.setLastName(params[1]);
             }

        // TODO: check if current author already in DB
            this.authorRepository.saveAndFlush(author);
        }
    }
}
