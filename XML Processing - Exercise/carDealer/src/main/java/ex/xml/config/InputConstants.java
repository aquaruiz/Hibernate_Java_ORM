package ex.xml.config;

import java.io.File;

public class InputConstants {
    public static final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";

    public static final String FILE_SUPPLIERS_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "suppliers.xml";
    public static final String FILE_PARTS_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "parts.xml";
    public static final String FILE_CARS_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "cars.xml";
    public static final String FILE_CUSTOMERS_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "customers.xml";
}