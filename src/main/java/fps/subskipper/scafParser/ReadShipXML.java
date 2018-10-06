package fps.subskipper.scafParser;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;


public class ReadShipXML {


    public Ships readXML() {

        Ships shipsXML = new Ships();
        try {
            File file = new File("data/shipList.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            shipsXML = (Ships) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
        return shipsXML;
    }

}
