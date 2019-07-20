package mostwanted.util;

import java.io.FileNotFoundException;

public interface JsonParser {

    <O> O[] parseJson(Class<O[]> objectClass, String filePath) throws FileNotFoundException;
}
