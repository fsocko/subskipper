package xmlParser;

import java.io.File;
import java.lang.invoke.MethodHandles;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class WriteTorpXML
{
	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
			public void writeXML(Ships shipWrap){
				
				try{
					JAXBContext jaxbContext = JAXBContext.newInstance(Torpedoes.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					File file = new File("data/torpList.xml");
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					jaxbMarshaller.marshal(shipWrap, System.out); //Was used for debugging,console out
					jaxbMarshaller.marshal(shipWrap, file);
				}
				catch(JAXBException e){}
			}	
}

