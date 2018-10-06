package fps.subskipper.scafparser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;

import static fps.subskipper.util.Constants.SHIPLIST_PATH;


public class WriteShipXML {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void writeXML(Ships shipWrapper) throws JAXBException {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            File file = new File(SHIPLIST_PATH);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.marshal(shipWrap, System.out); //Used in debugging to print xml to sys.out
            jaxbMarshaller.marshal(shipWrapper, file);
        }
        catch (JAXBException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
