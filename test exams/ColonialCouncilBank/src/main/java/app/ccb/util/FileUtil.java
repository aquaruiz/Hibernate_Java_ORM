package app.ccb.util;

import com.google.gson.Gson;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileUtil {

    String readFile(String filePath) throws IOException;

    <O> O[] parseFromJson(Gson gson, String filePath, Class<O[]> objClass) throws FileNotFoundException;

    <O> O unmarshallXml(String filePath, Class<O> objClass) throws JAXBException, FileNotFoundException;

}
