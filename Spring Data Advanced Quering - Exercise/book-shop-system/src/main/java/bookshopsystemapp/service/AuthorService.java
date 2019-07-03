package bookshopsystemapp.service;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    long getRecordsCount();

    List<String> getAuthorsByNameEnds(String letters);
}
