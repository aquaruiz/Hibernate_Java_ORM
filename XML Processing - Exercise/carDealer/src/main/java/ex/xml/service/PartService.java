package ex.xml.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface PartService {
    long getDbRecordsCount();

    void addPartsData() throws FileNotFoundException, JAXBException;
}
