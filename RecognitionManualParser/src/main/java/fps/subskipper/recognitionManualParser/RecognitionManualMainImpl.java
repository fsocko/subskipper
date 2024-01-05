package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.writers.ParseRecognitionManualLong;
import fps.subskipper.recognitionManualParser.writers.ParseRecognitionManualShort;
import fps.subskipper.*;
import ship.data.reader.IShipDataReader;
import ship.data.reader.sh4.ReadShipsFromSh4DataImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static fps.subskipper.util.Constants.SCAF_DATA_PATH;
import static fps.subskipper.util.Constants.SCAF_ROOT_PATH;

public class RecognitionManualMainImpl implements RecognitionManualMainInterface {

    @Override
    public Ships loadShipsToMemory() throws IOException {
        IShipDataReader shipDataReader = new ReadShipsFromSh4DataImpl();
        return shipDataReader.readShipsFromData(new File(SCAF_ROOT_PATH));
    }

    @Override
    public void publishRecognitionManualShort(Ships shipList, String filename, boolean isImperial, boolean aobTable) throws FileNotFoundException {
        ParseRecognitionManualShort parseShortManual = new ParseRecognitionManualShort();
        parseShortManual.writeRecogSHTML(shipList, filename, isImperial, aobTable);
    }

    @Override
    public void publishRecognitionManualLong(Ships shipList, String filename, boolean isImperial) throws FileNotFoundException {
        ParseRecognitionManualLong parseLongManual = new ParseRecognitionManualLong();
        parseLongManual.writeRecogLHTML(shipList, filename, isImperial);
    }
}
