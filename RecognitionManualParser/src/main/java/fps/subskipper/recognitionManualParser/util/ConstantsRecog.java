package fps.subskipper.recognitionManualParser.util;

import fps.subskipper.core.util.Constants;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;

import java.io.FileNotFoundException;
import java.lang.invoke.MethodHandles;
@Slf4j
@NoArgsConstructor
public class ConstantsRecog { //extends Constants {

    public static String RESOURCES_PATH = System.getProperty("user.dir") + "\\src\\main\\resources";
    public static String SHIPLIST_PATH = RESOURCES_PATH + "\\shipList.xml";
    public static String SCAF_ROOT_PATH = RESOURCES_PATH + "\\SCAF for TMO_2";
    public static String SCAF_DATA_PATH = SCAF_ROOT_PATH + "\\SCAF for TMO_2\\Data";
    public static String SCAF_NAMES_PATH = SCAF_ROOT_PATH + "\\Data\\Roster\\Names.cfg";
    public static String TARGET_PATH = System.getProperty("user.dir") + "\\target";
    public static String RECOGNITION_MANUALS_TARGET_PATH = TARGET_PATH + "\\recognitionManuals";

    public static final String RECOGNITION_MANUAL_LONG_FILENAME = "recognitionManual_long.html";
    public static final String RECOGNITION_MANUAL_SHORT_FILENAME = "recognitionManual_short.html";
    public static final String COMMAND_LINE_USAGE_MESSAGE = "*SubSkipper Recognition Manual Parser*" +
            "Usage: ";
}
