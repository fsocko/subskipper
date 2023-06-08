package uboat.ship.data.reader;

import fps.subskipper.core.Ships;
import fps.subskipper.core.util.TestUtilities;
import junit.framework.TestCase;
import java.io.File;
import java.net.URISyntaxException;

public class ReadUboatShipDataTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testReadShipsFromFile() throws URISyntaxException {


        ReadUboatShipData readUboatData = new ReadUboatShipData();
        File uboatDataFile = TestUtilities.getFileFromResources("excel_test_file.xlsx");
        System.out.println(uboatDataFile.getAbsoluteFile().toString());

        Ships uboatShips = readUboatData.readShipsFromFile(uboatDataFile);
        //TODO: System.out.println(uboatShips.toString());

        assertFalse("TODO: UboatReadShips is a quick POI example.", true);

    }
}