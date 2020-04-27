package fps.subskipper.recognitionManualParser;


import fps.subskipper.core.Ships;

import java.io.IOException;

public class RecogMain {

    public static void main(String[] args) throws IOException {

        Ships shipList = new RecognitionManualDataReader().loadShipsToMemory();
       // recogS.writeRecogSHTML(shipList, RecogConstants.RESOURCES_PATH + "\\recogSm.html", false, false);

    }
}
