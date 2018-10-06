package fps.subskipper.scafparser;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class WriteTorpXML {

    public void writeXML(Ships shipWrap) {

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Torpedoes.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            File file = new File("data/torpList.xml");
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(shipWrap, System.out); //Was used for debugging,console out
            jaxbMarshaller.marshal(shipWrap, file);
        } catch (JAXBException e) {
        }
    }
}

