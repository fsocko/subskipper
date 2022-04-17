package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ships;
import fps.subskipper.core.Torpedoes;
import fps.subskipper.recognitionManualParser.util.ConstantsRecog;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.lang.invoke.MethodHandles;
import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.*;


@Slf4j
public class EntityXmlMarshaller {


    public Ships readShipsFromXml() throws JAXBException, NullPointerException {
        try {
            File file = new File(SHIPLIST_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (Ships) jaxbUnmarshaller.unmarshal(file);
        }
        catch (JAXBException | NullPointerException e) {
            log.error("Failed to read ships from file:", e);
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
            log.info("Successfully wrote ships to file.");
        }
        catch (JAXBException e) {
            log.error("Failed to write ships to file:", e);
            throw e;
        }
    }
}
