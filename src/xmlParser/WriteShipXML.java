package xmlParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;


public class WriteShipXML {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void writeXML(Ships shipWrap) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            File file = new File("data/shipList.xml");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //jaxbMarshaller.marshal(shipWrap, System.out); //Was used for debugging,
            //but now it's just annoying.
            jaxbMarshaller.marshal(shipWrap, file);
        } catch (JAXBException e) {
        }
    }
}
