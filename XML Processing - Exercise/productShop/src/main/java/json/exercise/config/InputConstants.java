package json.exercise.config;

import java.io.File;

public class InputConstants {
    public static final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";
    public static final String FILE_CATEGORIES = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "categories.xml";
    public static final String FILE_USERS = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "users.xml";
    public static final String FILE_PRODUCTS = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "products.xml";
}
