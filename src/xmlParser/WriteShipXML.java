package xmlParser;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.ArrayList;

public class WriteShipXML {
	
	public void writeShipXML(){
		ReadSCAF SCAFToXML = new ReadSCAF();
		ArrayList<Ship> shipData = SCAFToXML.makeAllShips();
		for(int i = 0; i<shipData.size(); i++){
			writeXMLShip(shipData.get(i));
		}
	}
	
	public void writeXMLShip(Ship xmlShip){

	  try {
		File file = new File("shipData/shipData.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(Ship.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(xmlShip, file);
		jaxbMarshaller.marshal(xmlShip, System.out);
	  } 
	  
	  catch (JAXBException e) {
		  e.printStackTrace();}

	}
}
