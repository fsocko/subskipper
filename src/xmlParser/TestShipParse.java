package xmlParser;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

public class TestShipParse {

	public static void main(String[] args) throws JAXBException{
		
		//write ships to file
		WriteShipXML testXMLShip = new WriteShipXML();
		ReadSCAF scaf = new ReadSCAF();
		Ships runShips = new Ships();
		runShips.setShips(scaf.makeShips());
		testXMLShip.writeXML(runShips);
		
		ReadShipXML readShips = new ReadShipXML();
		System.out.println("********************************READING XML***********");
		runShips = readShips.readXML("shipData/testFile.xml");
		printShips(runShips);


	}

	public static void printShips(Ships printShips){
		
		for(int i = 0; i < printShips.getShips().size(); i++){
			System.out.println(printShips.getShips().get(i).toString());
		}
	}
}
