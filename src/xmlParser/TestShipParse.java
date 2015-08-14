package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestShipParse {

	public static void main(String[] args){

		ReadSCAF readTest = new ReadSCAF();	
		readTest.printSCAFFiles();
		readTest.makeAllShips();
		
		//WriteShipXML testXMLShip = new WriteShipXML();
		//testXMLShip.writeXMLShip();
		
	}

}
