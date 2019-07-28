package softuni.exam.util;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonParserImpl implements JsonParser {
    @Override
    public <O> O[] parseJSON(Gson gson, String filePath, Class<O[]> objClass) throws FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        O[] dtos = gson.fromJson(fr, objClass);
        return dtos;
    }
}
