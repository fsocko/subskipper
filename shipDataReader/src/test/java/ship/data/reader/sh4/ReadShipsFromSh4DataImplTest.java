package ship.data.reader.sh4;

import fps.subskipper.core.Ships;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import ship.data.reader.RMShip;

import java.io.File;
import java.io.IOException;

import static fps.subskipper.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReadShipsFromSh4DataImplTest {

    private final String testFile1 = SCAF_ROOT_PATH + SFS + "Data" + SFS + "Sea" + SFS + "AuxGunboat" + SFS + "AuxGunboat.cfg";
    private final String testFile118 = SCAF_ROOT_PATH + SFS + "Data" + SFS + "Sea" + SFS + "Sub_Depot_Ship" + SFS + "Sub_Depot_Ship.cfg";

    @Spy
    @InjectMocks
    private ReadShipsFromSh4DataImpl readShipsFromSh4Data = new ReadShipsFromSh4DataImpl();

    @BeforeEach
    void setUp() {

    }

    @Test
    void testListShipFiles() throws IOException {
        readShipsFromSh4Data.parseShipsFromScaf();
        assertEquals(118, readShipsFromSh4Data.getShipFiles().size());
        assertEquals(new File(testFile1), readShipsFromSh4Data.getShipFiles().get(0));
        assertEquals(new File(testFile118), readShipsFromSh4Data.getShipFiles().get(readShipsFromSh4Data.getShipFiles().size() - 1));
        System.out.print(readShipsFromSh4Data.getShipFiles().toString());
    }


    @Test
    void testParseShipsFromScaf() throws IOException {
        Ships parsedShips = readShipsFromSh4Data.parseShipsFromScaf();
        System.out.println(parsedShips.toString());
    }

    @Test
    void testMakeShip() throws IOException {

        // Act
        RMShip ship1 = readShipsFromSh4Data.makeShip(this.testFile1);
        RMShip ship118 = readShipsFromSh4Data.makeShip(this.testFile118);

        System.out.println(ship1.toString());
        System.out.println(ship118.toString());

        final String ship1ImagePath = SCAF_DATA_PATH + SFS + "Sea" + SFS + "AuxGunboat" + SFS + "AuxGunboat_sil.dds";
        final String ship118ImagePath = SCAF_DATA_PATH + SFS + "Sea" + SFS + "Sub_Depot_Ship" + SFS + "Sub_Depot_Ship_sil.dds";

        // Assert
        assertEquals("Small Old Split Freighter", ship1.getName());
        assertEquals(16, ship1.getType());
        assertEquals("Elite Patrol Craft", ship1.getTypeName());
        assertEquals(ship1ImagePath, ship1.getImagePath().replace("\"", ""));
        assertNotNull(ship1.getImage());
        assertEquals(17.0, ship1.getMaxSpeed());
        assertEquals(80.5, ship1.getLength());
        assertEquals(13.0, ship1.getWidth());
        assertEquals(17.9, ship1.getMast());
        assertEquals(3.7, ship1.getDraft());
        assertEquals(1850.0, ship1.getDisplacement());
        //Last ship in list, Ship118
        assertEquals("Submarine Tender", ship118.getName());
        assertEquals(100, ship118.getType());
        assertEquals("Replenishment", ship118.getTypeName());
        assertEquals(ship118ImagePath, ship118.getImagePath().replace("\"", ""));
        assertNotNull(ship118.getImage());
        assertEquals(18.0, ship118.getMaxSpeed());
        assertEquals(201.0, ship118.getLength());
        assertEquals(31.0, ship118.getWidth());
        assertEquals(22.4, ship118.getMast());
        assertEquals(9.4, ship118.getDraft());
        assertEquals(9250.0, ship118.getDisplacement());
    }
}