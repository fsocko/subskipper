package xmlParser;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;



public class WriteShipXML
{
	static Ships employees = new Ships();
	
	static 
	{
		employees.setEmployees(new ArrayList<Ship>());
		
		Ship emp = new Ship();
		emp.setID(1);
		emp.setName("Lokesh");
				
		
		employees.getEmployees().add(emp);
	}
	

	 public void marshalingExample() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Ships.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
 
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
 
		jaxbMarshaller.marshal(employees, System.out);
		jaxbMarshaller.marshal(employees, new File("/temp/employees.xml"));
	}
}
