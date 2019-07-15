package ex.xml.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CustomerService {
    long getDbRecordsCount();

    void addCustomersData() throws FileNotFoundException, JAXBException;

    void getOrderedCustomers() throws IOException, JAXBException;

    void getTotalSalesByCustomer() throws IOException, JAXBException;
}
