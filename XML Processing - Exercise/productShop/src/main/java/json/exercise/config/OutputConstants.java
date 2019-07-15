package json.exercise.config;

import java.io.File;

public class OutputConstants {
    public static final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";
    public static final String FILE_WRITE_PATH1 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "products-in-range.xml";
    public static final String FILE_WRITE_PATH2 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "users-sold-products.xml";
    public static final String  FILE_WRITE_PATH3 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "categories-by-products.xml";
    public static final String WRITE_PATH_QUERY4 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "users-and-products.xml";

}
