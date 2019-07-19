package softuni.workshop.service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ProjectService {

    void importProjects() throws FileNotFoundException, JAXBException;

    boolean areImported();

    String readProjectsXmlFile() throws IOException;

    String exportFinishedProjects() throws JAXBException, IOException;
}
