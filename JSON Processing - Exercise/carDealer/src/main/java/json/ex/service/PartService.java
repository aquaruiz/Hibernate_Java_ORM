package json.ex.service;

import java.io.FileNotFoundException;

public interface PartService {
    long getDbRecordsCount();

    void addPartsData() throws FileNotFoundException;
}
