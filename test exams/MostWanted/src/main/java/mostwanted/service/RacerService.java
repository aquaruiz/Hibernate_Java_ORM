package mostwanted.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface RacerService {

    Boolean racersAreImported();

    String readRacersJsonFile() throws IOException;

    String importRacers(String racersFileContent) throws FileNotFoundException;

    String exportRacingCars();
}
