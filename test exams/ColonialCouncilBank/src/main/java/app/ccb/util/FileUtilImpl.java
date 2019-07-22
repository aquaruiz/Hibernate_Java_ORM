package app.ccb.util;

import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        StringBuilder stringBuilder = new StringBuilder();

        String line;
        while((line = br.readLine()) != null){
            stringBuilder.append(line)
                        .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    @Override
    public <O> O[] parseFromJson(Gson gson, String filePath, Class<O[]> objClass) throws FileNotFoundException {
        FileReader fr = new FileReader(new File(filePath));
        return gson.fromJson(fr, objClass);
    }

    @Override
    public <O> O unmarshallXml(String filePath, Class<O> objClass) throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(objClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (O) unmarshaller.unmarshal(new File(filePath));
    }
}