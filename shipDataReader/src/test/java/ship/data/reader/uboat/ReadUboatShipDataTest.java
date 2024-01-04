package ship.data.reader.uboat;

import fps.subskipper.core.Ships;
import fps.subskipper.util.TestUtilities;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ReadUboatShipDataTest {

    public void setUp() throws Exception {

    }

    @Test
    public void testReadShipsFromFile() throws URISyntaxException {


        ReadUboatShipData readUboatData = new ReadUboatShipData();
        File uboatDataFile = TestUtilities.getFileFromResources("uboot/excel_test_file.xlsx");
        System.out.println(uboatDataFile.getAbsoluteFile().toString());

        Ships uboatShips = readUboatData.readShipsFromData(uboatDataFile);
        //System.out.println(uboatShips.toString());

        assertFalse(false, "TODO: UboatReadShips is a quick POI example.");

    }
}