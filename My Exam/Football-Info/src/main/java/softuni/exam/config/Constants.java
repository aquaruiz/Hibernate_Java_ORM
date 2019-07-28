package softuni.exam.config;

import java.io.File;
import java.math.BigDecimal;

public class Constants {
    private static String WORKING_FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";

    private static String INPUT_XML_FOLDER = WORKING_FOLDER_PATH + File.separator +
            "xml";
    private static String INPUT_JSON_FOLDER = WORKING_FOLDER_PATH + File.separator +
            "json";


    public static String PICTURES_IMPORT_PATH = INPUT_XML_FOLDER + File.separator +
            "pictures.xml";

    public static String TEAMS_IMPORT_PATH = INPUT_XML_FOLDER + File.separator +
            "teams.xml";

    public static String PLAYERS_IMPORT_PATH = INPUT_JSON_FOLDER + File.separator +
            "players.json";


    public static String TEAM_NAME = "North Hub";
    public static BigDecimal MIN_SALARY_SIZE = new BigDecimal(100000);
}