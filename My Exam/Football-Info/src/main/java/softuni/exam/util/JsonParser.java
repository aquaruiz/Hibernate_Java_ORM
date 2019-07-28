package softuni.exam.util;

import com.google.gson.Gson;

import java.io.FileNotFoundException;

public interface JsonParser {
    <O> O[] parseJSON(Gson gson, String filePath, Class<O[]> objClass) throws FileNotFoundException;
}
