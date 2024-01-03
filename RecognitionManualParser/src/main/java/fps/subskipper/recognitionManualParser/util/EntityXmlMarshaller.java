package fps.subskipper.recognitionManualParser.util;

import fps.subskipper.core.Ships;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.SHIPLIST_PATH;


@Slf4j

@Deprecated
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
