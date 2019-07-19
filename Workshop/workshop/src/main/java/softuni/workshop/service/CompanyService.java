package softuni.workshop.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CompanyService {

    void importCompanies() throws FileNotFoundException, JAXBException;

    boolean areImported();

    String readCompaniesXmlFile() throws IOException;
}
