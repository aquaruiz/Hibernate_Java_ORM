package alararestaurant.util;

import com.google.gson.Gson;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUtil {

    String readFile(String filePath) throws IOException;

    <O> O[] parseJSON(Gson gson, String filePath, Class<O[]> objClass) throws FileNotFoundException;

    <O> O parseXml(String filePath, Class<O> obj) throws JAXBException;
}
