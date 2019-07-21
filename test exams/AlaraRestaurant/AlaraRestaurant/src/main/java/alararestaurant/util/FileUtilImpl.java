package alararestaurant.util;

import alararestaurant.domain.dtos.EmployeeImportDto;
import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;

public class FileUtilImpl implements FileUtil {
    @Override
    public String readFile(String filePath) throws IOException {
        StringBuilder sBuilder = new StringBuilder();

        File file = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line;
        while ((line = br.readLine()) != null){
            sBuilder.append(line)
                    .append(System.lineSeparator());
        }

        return sBuilder.toString();
    }

    @Override
    public <O> O[] parseJSON(Gson gson, String filePath, Class<O[]> objClass) throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        O[] dtos = gson.fromJson(fr, objClass);
        return dtos;
    }

    @Override
    public <O> O parseXml(String filePath, Class<O> obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (O) unmarshaller.unmarshal(new File(filePath));
    }


}
