package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import fps.subskipper.core.Torpedoes;
import fps.subskipper.recognitionManualParser.util.ConstantsRecog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.*;

public class EntityXmlMarshallerTest {

    EntityXmlMarshaller entityXmlMarshaller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test @Ignore //TODO
    public void testReadShipsFromXml() throws Exception {
        //Ships result = entityXmlMarshaller.readShipsFromXml();
        //Assert.assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", "0d", 0d, 0d, 0d, 0d, 0d)))), result);
    }

    @Test @Ignore //TODO
    public void testWriteShipsToXml() throws Exception {
       //entityXmlMarshaller.writeShipsToXml(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", "0d", 0d, 0d, 0d, 0d, 0d)))));
       //Assert.assertEquals(new Ships(new ArrayList<Ship>(Arrays.asList(new Ship("name", 0, "typeName", "imagePath", "0d", 0d, 0d, 0d, 0d, 0d)))), entityXmlMarshaller.readShipsFromXml());
    }
}
