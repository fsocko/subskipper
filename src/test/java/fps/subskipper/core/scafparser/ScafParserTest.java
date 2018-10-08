package fps.subskipper.core.scafparser;

import fps.subskipper.core.Ships;
import fps.subskipper.scafparser.ScafParser;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class ScafParserTest {


    @Test @Ignore
    public void writeShipsToFile() throws JAXBException {

    }

    @Test
    public void loadShipsToMemory() throws JAXBException, IOException {
        Ships ships = new ScafParser().loadShipsToMemory();
        Assert.assertEquals(52, ships.getShips().size());
    }
}
