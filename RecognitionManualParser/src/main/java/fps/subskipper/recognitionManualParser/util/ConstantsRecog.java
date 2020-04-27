package fps.subskipper.recognitionManualParser.util;


import static fps.subskipper.recognitionManualParser.util.Find.findFile;

public class ConstantsRecog {

    //PATHS
    public static String RESOURCES_PATH;
    public static String SHIPLIST_PATH;
    public static String SCAF_ROOT_PATH;
    public static String SCAF_NAMES_PATH;
    public static String RECOGNITION_MANUALS_PATH;

    public static final String COMMAND_LINE_USAGE_MESSAGE = "*SubSkipper Recognition Manual Parser*" +
            "Usage: ";

    public ConstantsRecog() {

        this.RESOURCES_PATH = findFile(System.getProperty("user.dir"), "resources").getAbsolutePath();
        this.SHIPLIST_PATH = findFile(System.getProperty("user.dir"), "resources").getAbsolutePath();
        this.SCAF_ROOT_PATH = findFile(System.getProperty("user.dir"), "SCAF for TMO_2").getAbsolutePath();
        this.SCAF_NAMES_PATH = findFile(SCAF_ROOT_PATH, "Names.cfg").getAbsolutePath();
        this.RECOGNITION_MANUALS_PATH = RESOURCES_PATH + "\\recognitionManuals";

    }

}
