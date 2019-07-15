package xml.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ProductService {
    long getRecordsCount();

    void seedProducts() throws IOException, JAXBException;

    void collectProductsInRange(int lowerboundary, int upperboundary) throws IOException, JAXBException;

    void collectUsersWithSuccessfullySoldProducts() throws IOException, JAXBException;

}
