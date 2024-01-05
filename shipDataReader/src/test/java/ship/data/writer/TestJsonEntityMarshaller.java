package ship.data.writer;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static fps.subskipper.util.Constants.SHIPLIST_PATH;

public class TestJsonEntityMarshaller {
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


        @Test
        public void testWriteShipsToJsonFile() throws IOException {
            // Create a list of ships
            ArrayList<Ship> shipList = new ArrayList<>();
            shipList.add(new Ship("Ship1", 1, "Type1", "image1.jpg", 10.0, 100.0, 50.0, 20.0, 5.0, 200.0));
            shipList.add(new Ship("Ship2", 2, "Type2", "image2.jpg", 15.0, 120.0, 60.0, 25.0, 6.0, 250.0));
            Ships ships = new Ships();
            ships.setShips(shipList);

            // Write ships to JSON file
            JsonEntityMarshaller.writeShipsToJsonFile(ships, new File(SHIPLIST_PATH));

            // Read the contents of the JSON file
            String jsonContent = new String(Files.readAllBytes(Paths.get(SHIPLIST_PATH)));

            // Verify that the JSON content matches the expected output
            String expectedJson = "[{\"id\":1,\"nation\":\"none\",\"name\":\"Ship1\",\"type\":1,\"typeName\":\"Type1\",\"image\":\"image1.jpg\",\"maxSpeed\":10.0,\"length\":100.0,\"width\":50.0,\"mast\":20.0,\"draft\":5.0,\"displacement\":200.0,\"refAspect\":5.0},{\"id\":2,\"nation\":\"none\",\"name\":\"Ship2\",\"type\":2,\"typeName\":\"Type2\",\"image\":\"image2.jpg\",\"maxSpeed\":15.0,\"length\":120.0,\"width\":60.0,\"mast\":25.0,\"draft\":6.0,\"displacement\":250.0,\"refAspect\":4.8}]";
            Assertions.assertEquals(expectedJson, jsonContent);

            // Clean up the test file
            Files.deleteIfExists(Paths.get(SHIPLIST_PATH));
        }

        @Test
        public void testReadShipsFromFile() throws IOException {
            // Create a JSON file with ships
            String jsonContent = "[{\"id\":1,\"nation\":\"none\",\"name\":\"Ship1\",\"type\":1,\"typeName\":\"Type1\",\"imagePath\":\"image1.jpg\",\"maxSpeed\":10.0,\"length\":100.0,\"width\":50.0,\"mast\":20.0,\"draft\":5.0,\"displacement\":200.0,\"refAspect\":5.0},{\"id\":1,\"nation\":\"none\",\"name\":\"Ship2\",\"type\":2,\"typeName\":\"Type2\",\"imagePath\":\"image2.jpg\",\"maxSpeed\":15.0,\"length\":120.0,\"width\":60.0,\"mast\":25.0,\"draft\":6.0,\"displacement\":250.0,\"refAspect\":4.8}]";
            Files.write(Paths.get(SHIPLIST_PATH), jsonContent.getBytes());

            // Read ships from the JSON file
            Ships ships = JsonEntityMarshaller.readShipsFromFile(new File(SHIPLIST_PATH));

                        // Verify that the ships are read correctly
            List<Ship> expectedShipList = new ArrayList<>();
            expectedShipList.add(new Ship("Ship1", 1, "Type1", "image1.jpg", 10.0, 100.0, 50.0, 20.0, 5.0, 200.0));
            expectedShipList.add(new Ship("Ship2", 2, "Type2", "image2.jpg", 15.0, 120.0, 60.0, 25.0, 6.0, 250.0));
            Ships expectedShips = new Ships(expectedShipList);
            Assertions.assertEquals(expectedShips, ships);

            // Clean up the test file
            Files.deleteIfExists(Paths.get(SHIPLIST_PATH));
        }
    }