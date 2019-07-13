package json.ex.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CarService {
    long getDbRecordsCount();

    void addCarsData() throws FileNotFoundException;

    void getCarsFromMake(String carMake) throws IOException;

    void getCarsWithPartsList() throws IOException;
}
