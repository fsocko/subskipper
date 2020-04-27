package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.RecognitionManualMainInterface;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class RecognitionManualDataReaderTest {


    @Test @Ignore
    public void writeShipsToFile() throws JAXBException {

    }

    @Test
    public void loadShipsToMemoryTest() throws JAXBException, IOException {
        RecognitionManualMainInterface parser = new RecognitionManualMainInterface();

        Ships ships = parser.loadShipsToMemory();
        Assert.assertEquals(52, ships.getShips().size());
    }
}
