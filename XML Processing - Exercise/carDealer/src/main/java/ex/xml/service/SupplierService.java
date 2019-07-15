package ex.xml.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface SupplierService {
    long getDbRecordsCount();

    void addSuppliersData() throws FileNotFoundException, JAXBException;

    void getLocalSuppliers() throws IOException, JAXBException;
}
