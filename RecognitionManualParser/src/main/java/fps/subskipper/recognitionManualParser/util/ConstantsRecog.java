package fps.subskipper.recognitionManualParser.util;


import fps.subskipper.core.util.Constants;
import org.apache.logging.log4j.LogManager;

import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;

public class ConstantsRecog extends Constants {

    final static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static String RESOURCES_PATH;
    public static String SHIPLIST_PATH;
    public static String SCAF_ROOT_PATH;
    public static String SCAF_NAMES_PATH;
    public static String RECOGNITION_MANUALS_TARGET_PATH;

    public static final String RECOGNITION_MANUAL_LONG_FILENAME = "recognitionManual_long.html";
    public static final String RECOGNITION_MANUAL_SHORT_FILENAME = "recognitionManual_short.html";
    public static final String COMMAND_LINE_USAGE_MESSAGE = "*SubSkipper Recognition Manual Parser*" +
            "Usage: ";

    public ConstantsRecog() {

        super();

        try {
            RESOURCES_PATH = Find.findFile(System.getProperty("user.dir"), "resources").getAbsolutePath();
            SHIPLIST_PATH = Find.findFile(System.getProperty("user.dir"), "resources").getAbsolutePath();
            SCAF_ROOT_PATH = Find.findFile(System.getProperty("user.dir"), "SCAF for TMO_2").getAbsolutePath();
            SCAF_NAMES_PATH = Find.findFile(SCAF_ROOT_PATH, "Names.cfg").getAbsolutePath();
            RECOGNITION_MANUALS_TARGET_PATH = RESOURCES_PATH + "\\recognitionManuals";
            logger.info("Set the paths: {} {} {} {}", RESOURCES_PATH, SHIPLIST_PATH, SCAF_ROOT_PATH, RECOGNITION_MANUALS_TARGET_PATH);
        }
        catch (FileNotFoundException f){
            logger.fatal("Unrecoverable exception, paths could not be set: {}", f);
            System.exit(1);
        }
    }

}
