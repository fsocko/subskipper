package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface RecognitionManualMainInterface {

    //ReadShipsFromManual
    Ships loadShipsToMemory() throws IOException;

    //Write ships to xml file.
    void writeShipsToFile(Ships ships) throws JAXBException;

    //Publish Short Manual
    void writeRecogSHTML(Ships shipList, String filename, boolean imperial, boolean AOBTable) throws FileNotFoundException;

    //Publish Long Manual
    void writeRecogLHTML(Ships shipList, String filename, boolean imperial) throws FileNotFoundException;

}
