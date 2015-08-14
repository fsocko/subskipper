package xmlParser;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class WriteShipXML {
	
	public void writeXMLShip(Ship xmlShip){

	  	  
	  try {

		File file = new File("shipData/testShip.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Ship.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(xmlShip, file);
		jaxbMarshaller.marshal(xmlShip, System.out);

	  } 
	  
	  catch (JAXBException e) {
		  e.printStackTrace();}

	}
}
