package xmlParser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;


public class ReadTorpXML {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public Torpedoes readXML() {

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

}
