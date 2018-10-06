package fps.subskipper.scafparser;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;


public class ReadTorpXML {

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
