package json.exercise.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProductService {
    long getRecordsCount();

    void seedProducts() throws IOException;

    void collectProductsInRange(int lowerboundary, int upperboundary) throws IOException;

    void collectUsersWithSuccessfullySoldProducts() throws IOException;
}
