package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.writers.ParseRecognitionManualLong;
import fps.subskipper.recognitionManualParser.writers.ParseRecognitionManualShort;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RecognitionManualMainImpl implements RecognitionManualMainInterface {

    @Override
    public Ships loadShipsToMemory() throws IOException {
     return null;
    }

    @Override
    public void publishRecognitionManualShort(Ships shipList, String filename, boolean imperial, boolean aobTable) throws FileNotFoundException {
        ParseRecognitionManualShort parseShortManual = new ParseRecognitionManualShort();
        parseShortManual.writeRecogSHTML(shipList, filename, imperial, aobTable);
    }

    @Override
    public void publishRecognitionManualLong(Ships shipList, String filename, boolean imperial) throws FileNotFoundException {
        ParseRecognitionManualLong parseLongManual = new ParseRecognitionManualLong();
        parseLongManual.writeRecogLHTML(shipList, filename, imperial);
    }
}
