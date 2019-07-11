package json.ex.service;

import java.io.FileNotFoundException;

public interface SupplierService {
    long getDbRecordsCount();

    void addSuppliersData() throws FileNotFoundException;
}
