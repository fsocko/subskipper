package xmlParser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;



public class ReadShipXML {

	public Ships readXML(String filePath){
		 
		Ships shipsXML = new Ships();
		try{
	            File file = new File(filePath);
	            JAXBContext jaxbContext = JAXBContext.newInstance( Ships.class );

	            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	            shipsXML = (Ships)jaxbUnmarshaller.unmarshal( file );     
	        }
		catch( JAXBException e ){
	            e.printStackTrace();
	        }
		return shipsXML;
	}
	
}