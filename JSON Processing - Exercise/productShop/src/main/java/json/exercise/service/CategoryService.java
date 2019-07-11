package json.exercise.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws FileNotFoundException;

    long getRecordsCount();

    void getStatisticsByProductsCount() throws IOException;
}
