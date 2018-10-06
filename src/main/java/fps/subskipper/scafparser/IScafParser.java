package fps.subskipper.scafparser;

import fps.subskipper.core.Ships;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface IScafParser {

    void writeShipsToFile(Ships ships) throws JAXBException;

    /**
     * This method attempts to read Ships from XML. if no XML is found,
     * It parses SCAF and creates the XML file, then returns Ships object.
     */
    Ships loadShipsToMemory() throws JAXBException, IOException;


//TODO:
//    public void writeTorpedoXml();
//
//    public List<Torpedo> readTorpedoXml();


}
