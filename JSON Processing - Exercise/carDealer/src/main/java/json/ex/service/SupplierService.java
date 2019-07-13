package json.ex.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface SupplierService {
    long getDbRecordsCount();

    void addSuppliersData() throws FileNotFoundException;

    void getLocalSuppliers() throws IOException;
}
