package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class RecognitionManualDataReaderTest {
    
	
    @InjectMocks
    RecognitionManualDataReader recognitionManualDataReader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test @Ignore //BROKEN! FIXME
    public void testWriteShipsToFile() throws Exception {
        recognitionManualDataReader.writeShipsToFile(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))));
    }

    @Test
    public void testLoadShipsToMemory() throws Exception { //BROKEN FIXME
        Ships result = recognitionManualDataReader.loadShipsToMemory();
        Assert.assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), result);
    }

    @Test
    public void testParseShipsFromScaf() throws Exception {
        Ships result = recognitionManualDataReader.parseShipsFromScaf();
        Assert.assertEquals(118, result.getShips().size());
    }

    @Test
    public void testMakeShip() throws Exception {
        Ship result = recognitionManualDataReader.makeShip("path");
        Assert.assertEquals(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d), result);
    }

    @Test
    public void testListShipFiles() throws Exception {
        recognitionManualDataReader.listShipFiles("directoryName", new ArrayList<File>(Arrays.asList(new File(getClass().getResource("/fps/subskipper/recognitionManualParser/PleaseReplaceMeWithTestFile.txt").getFile()))));
    }
}
