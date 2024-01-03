package fps.subskipper.recognitionManualParser.util;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityXmlMarshallerTest {

    EntityXmlMarshaller entityXmlMarshaller;

    @BeforeAll
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        entityXmlMarshaller = new EntityXmlMarshaller();
    }

    @Test
    public void testReadShipsFromXml() throws Exception {
        Ships result = entityXmlMarshaller.readShipsFromXml();
        assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), result);
    }

    @Test
    public void testWriteShipsToXml() throws Exception {
       entityXmlMarshaller.writeShipsToXml(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))));
       assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), entityXmlMarshaller.readShipsFromXml());
    }
}
