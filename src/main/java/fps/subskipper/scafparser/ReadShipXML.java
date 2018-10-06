package fps.subskipper.scafparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;

import static fps.subskipper.util.Constants.SHIPLIST_PATH;


public class ReadShipXML {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public Ships readXML() throws JAXBException {

        try {
            File file = new File(SHIPLIST_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Ships shipsXml = (Ships) jaxbUnmarshaller.unmarshal(file);
            return shipsXml;
        }
        catch (JAXBException e) {
           logger.error(e.getMessage());
           throw e;
        }
    }

}
