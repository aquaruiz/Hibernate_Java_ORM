package mostwanted.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    @Override
    public String readFile(String filePath) throws IOException {
        File file = new File(filePath);
        FileReader fileStream = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fileStream);

        StringBuilder sBuilder = new StringBuilder();

        String line;
        while((line = bReader.readLine()) != null) {
            sBuilder.append(line).append(System.lineSeparator());
        }

        return sBuilder.toString();
    }
}