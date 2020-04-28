package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RecognitionManualMainImpl implements RecognitionManualMainInterface {

    @Override
    public Ships loadShipsToMemory() throws IOException {
        RecognitionManualDataReader reader = new RecognitionManualDataReader();
        return reader.loadShipsToMemory();
    }

    @Override
    public void writeShipsToFile(Ships ships) throws JAXBException {
        RecognitionManualDataReader reader = new RecognitionManualDataReader();
        reader.writeShipsToFile(ships);
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
