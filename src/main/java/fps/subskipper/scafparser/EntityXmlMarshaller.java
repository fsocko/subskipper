package fps.subskipper.scafparser;

import fps.subskipper.core.Ships;
import fps.subskipper.core.Torpedoes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;

import static fps.subskipper.util.Constants.SHIPLIST_PATH;

public class EntityXmlMarshaller {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public Ships readShipsFromXml() throws JAXBException {
        try {
            File file = new File(SHIPLIST_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Ships shipsXml = (Ships) jaxbUnmarshaller.unmarshal(file);
            return shipsXml;
        }
        catch (JAXBException e) {
            logger.error("Failed to read ships from file:", e.getMessage());
            throw e;
        }
    }

    public void writeShipsToXml(Ships shipWrapper) throws JAXBException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            File file = new File(SHIPLIST_PATH);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.marshal(shipWrap, System.out); //Used in debugging to print xml to sys.out
            jaxbMarshaller.marshal(shipWrapper, file);
            logger.info("Successfully wrote ships to file.");
        }
        catch (JAXBException e) {
            logger.error("Failed to write ships to file:", e.getMessage());
            throw e;
        }
    }


    //TODO:

    public Torpedoes readTorpedoXml() {

        Torpedoes torpsXML = new Torpedoes();
        try {
            File file = new File("data/torpList.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Torpedoes.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            torpsXML = (Torpedoes) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return torpsXML;
    }

    public boolean writeTorpedoXml(Ships shipWrap) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Torpedoes.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            File file = new File("data/torpList.xml");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(shipWrap, System.out); //Was used for debugging,console out
            jaxbMarshaller.marshal(shipWrap, file);
            return true;
        }
        catch (JAXBException e) {
            return false;
        }
    }
}
