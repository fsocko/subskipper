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
		testXMLShip.marshalingExample(runShips);


	}

	public static void printShips(ArrayList shipData){
		for(int i = 0; i < shipData.size(); i++){
			System.out.println(shipData.get(i).toString());
		}
	}
}
