package json.ex.service;

import java.io.FileNotFoundException;

public interface CarService {
    long getDbRecordsCount();

    void addCarsData() throws FileNotFoundException;
}
