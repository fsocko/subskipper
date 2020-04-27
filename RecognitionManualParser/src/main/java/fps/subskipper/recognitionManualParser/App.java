package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.util.ConstantsRecog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

/**
 * Simple main application
 *
 * Params:
 *
 *
 */
public class App {

        final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
        RecognitionManualMainImpl recognitionManualMain = new RecognitionManualMainImpl();
        final Ships shipList = recognitionManualMain.loadShipsToMemory();

    public App() throws IOException {
    }

    public static void main(String[] args) {

            System.out.println(ConstantsRecog.COMMAND_LINE_USAGE_MESSAGE);
            //TODO:
            //Output path -- Default to Target/Generate filename based on attributes. - Change title based on attributes i.e. not always SCAF
            //Imperial
            //AobTable (Short Only)

        }

        public void publishShortRecognitionManual() throws IOException {
           recognitionManualMain.writeRecogSHTML(shipList, ConstantsRecog.RESOURCES_PATH + "\\recogSm.html", false, false);
        }

        public void publishLongRecognitionManual() throws IOException {
            recognitionManualMain.writeRecogLHTML(shipList, ConstantsRecog.RESOURCES_PATH + "\\recogLm.html", false);
        }



//    public static void main(String[] args) {
//
//        if (args.length != 2) {
//            throw new IllegalArgumentException("Args must be two numerical values.");
//        }
//
//        final String inputError = String.format("Args must be two numerical values, a and b where a<b and b<2,147,483,647. Args are: %s and %s", args[0], args[1]);
//
//        int a;
//        int b;
//        try {
//            a = Integer.parseInt(args[0]);
//            b = Integer.parseInt(args[1]);
//        } catch (NumberFormatException n) {
//            throw new NumberFormatException("Arguments are not numerical. " + inputError);
//        }



}



