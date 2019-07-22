package app.ccb.services;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ClientService {

    Boolean clientsAreImported();

    String readClientsJsonFile() throws IOException;

    String importClients(String clients) throws FileNotFoundException;

    String exportFamilyGuy();
}
