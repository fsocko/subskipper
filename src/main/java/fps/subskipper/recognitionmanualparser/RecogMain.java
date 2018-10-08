package fps.subskipper.recognitionmanualparser;


import fps.subskipper.core.Ships;
import fps.subskipper.scafparser.ScafParser;
import fps.subskipper.util.Constants;

import java.io.IOException;

public class RecogMain {

    public static void main(String[] args) throws IOException {

        ParseRecognitionManualShort recogS = new ParseRecognitionManualShort();

            Ships shipList = new ScafParser().loadShipsToMemory();
            recogS.writeRecogSHTML(shipList, Constants.RESOURCES_PATH + "\\recogSm.html", false, false);

    }
}
