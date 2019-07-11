package json.ex.service;

import java.io.FileNotFoundException;

public interface CustomerService {
    long getDbRecordsCount();

    void addCustomersData() throws FileNotFoundException;
}
