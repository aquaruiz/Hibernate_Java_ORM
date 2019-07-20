package mostwanted.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonParserImpl implements JsonParser {

    @Override
    public <O> O[] parseJson(Class<O[]> objectClass, String filePath) throws FileNotFoundException {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        FileReader fileReader = new FileReader(filePath);
        O[] objects = gson.fromJson(fileReader, objectClass);
        return objects;
    }
}
