package fps.subskipper.recognitionManualParser;

import fps.subskipper.recognitionManualParser.util.RecogConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

        public static void main(String[] args) {

            System.out.println(RecogConstants.COMMAND_LINE_USAGE_MESSAGE);

        }



}



