package xmlParser;

import java.io.File;
import java.lang.invoke.MethodHandles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ReadShipXML {

	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	public Ships readXML(){
		 
		Ships shipsXML = new Ships();
		try{
	            File file = new File("data/shipList.xml");
	            JAXBContext jaxbContext = JAXBContext.newInstance( Ships.class );

	            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	            shipsXML = (Ships)jaxbUnmarshaller.unmarshal( file );     
	        }
		catch( JAXBException e ){
	            e.printStackTrace();
	            return null;
	        }
		return shipsXML;
	}
	
}
