package fps.subskipper.recognitionManualParser.util;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

public class EntityXmlMarshallerTest {

    EntityXmlMarshaller entityXmlMarshaller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        entityXmlMarshaller = new EntityXmlMarshaller();
    }

    @Test
    public void testReadShipsFromXml() throws Exception {
        Ships result = entityXmlMarshaller.readShipsFromXml();
        Assert.assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), result);
    }

    @Test
    public void testWriteShipsToXml() throws Exception {
       entityXmlMarshaller.writeShipsToXml(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))));
       Assert.assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", 0d, 0d, 0d, 0d, 0d, 0d)))), entityXmlMarshaller.readShipsFromXml());
    }
}
