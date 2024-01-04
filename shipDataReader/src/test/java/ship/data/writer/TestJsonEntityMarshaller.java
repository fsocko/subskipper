package ship.data.writer;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJsonEntityMarshaller {
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testReadShipsFromJson() throws Exception {
        Ships result = JsonEntityMarshaller.readShipsFromFile();
        assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), result);
    }

    @Test
    public void testWriteShipsToJson() throws Exception {
        JsonEntityMarshaller.writeShipsToJsonFile(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))));
        assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), JsonEntityMarshaller.readShipsFromFile());
    }
}
