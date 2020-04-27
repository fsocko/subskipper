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
    public void writeRecogSHTML(Ships shipList, String filename, boolean imperial, boolean AOBTable) throws FileNotFoundException {
        ParseRecognitionManualShort parseShortManual = new ParseRecognitionManualShort();
        parseShortManual.writeRecogSHTML(shipList, filename, imperial, AOBTable);
    }

    @Override
    public void writeRecogLHTML(Ships shipList, String filename, boolean imperial) throws FileNotFoundException {
        ParseRecognitionManualLong parseLongManual = new ParseRecognitionManualLong();
        parseLongManual.writeRecogLHTML(shipList, filename, imperial);
    }
}
