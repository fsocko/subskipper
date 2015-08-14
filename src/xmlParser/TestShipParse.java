package xmlParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestShipParse {

	public static void main(String[] args){

		ReadSCAF readTest = new ReadSCAF();	
		WriteShipXML testXMLShip = new WriteShipXML();

		testXMLShip.writeShipXML();	
	}

	public static void printShips(ArrayList shipData){
		for(int i = 0; i < shipData.size(); i++){
			System.out.println(shipData.get(i).toString());
		}
	}
}
