package ex.xml.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CarService {
    long getDbRecordsCount();

    void addCarsData() throws FileNotFoundException, JAXBException;

    void getCarsFromMake(String carMake) throws IOException, JAXBException;

    void getCarsWithPartsList() throws IOException, JAXBException;
}
