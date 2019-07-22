package app.ccb.config;

import java.io.File;

public class Constants {
    public static String FOLDER_JSON_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator +
            "json";
    public static String BRANCHES_IMPORT_PATH = FOLDER_JSON_PATH + File.separator +
            "branches.json";
    public static String EMPLOYEES_IMPORT_PATH = FOLDER_JSON_PATH + File.separator +
            "employees.json";
    public static String CLIENTS_IMPORT_PATH = FOLDER_JSON_PATH + File.separator +
            "clients.json";

    public static String FOLDER_XML_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator +
            "xml";
    public static String BANK_ACCOUNTS_IMPORT_PATH = FOLDER_XML_PATH + File.separator +
            "bank-accounts.xml";
    public static String CARDS_IMPORT_PATH = FOLDER_XML_PATH + File.separator +
            "cards.xml";
}
