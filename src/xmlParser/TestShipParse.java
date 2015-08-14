package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestShipParse {

	public static void main(String[] args){

		ReadShips readTest = new ReadShips();	
		//readTest.printShipFiles();
		//readTest.makeAllShips();
		
		WriteShipXML customer = new WriteShipXML();
		customer.writeCustomer();
		
	}

}
