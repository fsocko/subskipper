package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.writers.ParseRecognitionManualShort;
import fps.subskipper.util.Constants;
import ship.data.reader.IShipDataReader;
import ship.data.reader.uboat.ReadUboatShipData;
import ship.data.reader.uboat.ReadUboatShipData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static fps.subskipper.util.Constants.UBOOT_ENTITIES_PATH;

public class RecognitionManualUbootImpl implements RecognitionManualMainInterface {

    @Override
    public Ships loadShipsToMemory(File dataPath) throws IOException {
        IShipDataReader ubootDataReader = new ReadUboatShipData();
        return ubootDataReader.readShipsFromData(dataPath);
    }

    @Override
    public Ships loadShipsToMemory() throws IOException {
        IShipDataReader ubootDataReader = new ReadUboatShipData();
        return ubootDataReader.readShipsFromData(new File(UBOOT_ENTITIES_PATH));
    }

    @Override
    public void publishRecognitionManualShort(Ships shipList, String filename, boolean isImperial, boolean isGenerateAOBTable)
            throws FileNotFoundException {
        ParseRecognitionManualShort parseShortManual = new ParseRecognitionManualShort();
        parseShortManual.writeRecogSHTML(shipList, filename, Constants.UBOAT_TITLE, isImperial, isGenerateAOBTable);
    }

    @Override
    public void publishRecognitionManualLong(Ships shipList, String filename, boolean isImperial) throws FileNotFoundException {
        throw new UnsupportedOperationException();
    }

}
