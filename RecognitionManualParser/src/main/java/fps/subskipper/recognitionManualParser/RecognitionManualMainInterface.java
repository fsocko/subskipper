package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface RecognitionManualMainInterface {

    Ships loadShipsToMemory(File dataPath) throws IOException;

    Ships loadShipsToMemory() throws IOException;

    //Publish Short Manual
    void publishRecognitionManualShort(Ships shipList, String filename, boolean imperial, boolean AOBTable) throws FileNotFoundException;

    //Publish Long Manual
    void publishRecognitionManualLong(Ships shipList, String filename, boolean imperial) throws FileNotFoundException;

}
