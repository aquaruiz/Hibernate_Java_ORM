package json.exercise.service;

import java.io.FileNotFoundException;

public interface CategoryService {
    void seedCategories() throws FileNotFoundException;

    long getRecordsCount();
}
