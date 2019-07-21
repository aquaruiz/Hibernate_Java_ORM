package alararestaurant.config;

import java.io.File;
import java.io.FileInputStream;

public class Constants {

    public static String FILE_FOLDER_PATH = "src" + File .separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";

    public static String EMPLOYEES_IMPORT_PATH = FILE_FOLDER_PATH + File.separator +
            "employees.json";
    public static String ITEMS_IMPORT_PATH = FILE_FOLDER_PATH + File.separator +
            "items.json";
    public static String ORDERS_IMPORT_PATH = FILE_FOLDER_PATH + File.separator +
            "orders.xml";
}
