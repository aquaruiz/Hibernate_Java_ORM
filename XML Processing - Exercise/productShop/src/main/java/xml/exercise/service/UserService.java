package xml.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {
    long getRecordsCount();

    void seedUsers() throws FileNotFoundException, JAXBException;

    void getUsersAndSoldProducts() throws IOException, JAXBException;
}
