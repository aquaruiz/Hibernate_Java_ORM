package softuni.workshop.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUtil {

    BufferedReader readFile(String path) throws FileNotFoundException;

    String getXmltoString(String employeesPathImport) throws IOException;
}
