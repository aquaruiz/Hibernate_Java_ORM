package mostwanted.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface CarService {

    Boolean carsAreImported();

    String readCarsJsonFile() throws IOException;

    String importCars(String carsFileContent) throws FileNotFoundException;
}
