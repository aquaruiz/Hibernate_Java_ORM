package json.exercise.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws FileNotFoundException, JAXBException;

    long getRecordsCount();

    void getStatisticsByProductsCount() throws IOException, JAXBException;
}
