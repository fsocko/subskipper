package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecognitionManualDataReaderTest {
    
	
    @InjectMocks
    RecognitionManualDataReader recognitionManualDataReader = new RecognitionManualDataReader();

    @Test
    @Disabled //BROKEN! FIXME
    public void testWriteShipsToFile() throws Exception {
        recognitionManualDataReader.writeShipsToFile(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))));
    }

    @Test @Disabled //FIXME
    public void testLoadShipsToMemory() throws Exception { //BROKEN FIXME
        Ships result = recognitionManualDataReader.loadShipsToMemory();
        assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), result);
    }

    @Test
    public void testParseShipsFromScaf() throws Exception {
        Ships result = recognitionManualDataReader.parseShipsFromScaf();
        assertEquals(118, result.getShipList().size());
    }

    @Test @Disabled //FIXME: BR is null
    public void testMakeShip() throws Exception {
        Ship result = recognitionManualDataReader.makeShip("path");
        assertEquals(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d), result);
    }

    @Test @Disabled
    public void testListShipFiles() throws Exception {
        recognitionManualDataReader.listShipFiles("directoryName", new ArrayList<File>(Arrays.asList(new File(getClass().getResource("/fps/subskipper/recognitionManualParser/PleaseReplaceMeWithTestFile.txt").getFile()))));
    }
}
