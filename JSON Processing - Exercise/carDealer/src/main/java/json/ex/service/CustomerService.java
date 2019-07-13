package json.ex.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CustomerService {
    long getDbRecordsCount();

    void addCustomersData() throws FileNotFoundException;

    void getOrderedCustomers() throws IOException;
}
